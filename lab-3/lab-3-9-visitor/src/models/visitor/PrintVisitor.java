package models.visitor;

import models.vehicle.*;

import java.util.Arrays;

public class PrintVisitor implements Visitor {

    enum PrintFormat {
        ROW,
        COLUMN
    }

    @Override
    public void visit(Motorcycle moto) {
        System.out.println(this.toFormattedString(moto, PrintFormat.COLUMN));
    }

    @Override
    public void visit(Car car) {
        System.out.println(this.toFormattedString(car, PrintFormat.ROW));
    }

    private String toFormattedString(Vehicle vehicle, PrintFormat format) {
        StringBuilder builder = new StringBuilder();
        builder.append("Auto brand: ")
                .append(vehicle.getVehicleBrand())
                .append(";");

        if (format == PrintFormat.COLUMN) {
            builder.append("\n");
        }

        var names = vehicle.getModelNames();
        builder.append("Auto models: [");

        if (format == PrintFormat.COLUMN) {
            builder.append("\n");
        }
        Arrays.stream(names).forEachOrdered(value -> {
            builder.append("{ name: ").append(value);
            try {
                var price = vehicle.getModelPriceByName(value);
                builder.append(", price: ").append(price);
            } catch (Exception e) {
                e.printStackTrace();
            }
            builder.append(" },");

            if (format == PrintFormat.COLUMN) {
                builder.append("\n");
            }
        });
        builder.append("];");

        if (format == PrintFormat.COLUMN) {
            builder.append("\n");
        }

        return builder.toString();
    }
}
