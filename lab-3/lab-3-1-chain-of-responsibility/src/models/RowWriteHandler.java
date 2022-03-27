package models;

import factory_method.exeptions.NoSuchModelNameException;
import factory_method.models.Vehicle;

import java.io.*;
import java.util.Arrays;

public class RowWriteHandler implements WriteHandler {
    private WriteHandler handler;
    private final Writer writer;

    public RowWriteHandler(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(Vehicle vehicle) throws IOException {
        if (vehicle.getModelsSize() <= 3) {
            this.writer.write(toString(vehicle));
            this.writer.flush();
        } else if (handler != null) {
            this.handler.write(vehicle);
        }
    }

    private String toString(Vehicle vehicle) {
        StringBuilder builder = new StringBuilder();
        builder.append("Vehicle brand: ").append(vehicle.getVehicleBrand()).append("; ");
        var names = vehicle.getModelNames();
        builder.append("Vehicle models: [");
        Arrays.stream(names).forEachOrdered(value -> {
            builder.append("{ name: ").append(value);
            try {
                var price = vehicle.getModelPriceByName(value);
                builder.append(", price: ").append(price);
            } catch (NoSuchModelNameException e) {
                e.printStackTrace();
            }
            builder.append(" }, ");
        });
        builder.append("];\n");

        return builder.toString();
    }

    @Override
    public void setHandler(WriteHandler handler) {
        this.handler = handler;
    }
}
