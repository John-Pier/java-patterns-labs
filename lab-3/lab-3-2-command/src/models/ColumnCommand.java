package models;

import factory_method.exeptions.NoSuchModelNameException;

import java.io.*;
import java.util.Arrays;

public class ColumnCommand implements Command {
    private final Auto auto;

    public ColumnCommand(Auto auto) {
        this.auto = auto;
    }

    @Override
    public void execute() {
        var writer = auto.getWriter();
        try {
            writer.write("\n");
            writer.write(toColumnString(auto));
            writer.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String toColumnString(Auto auto) {
        StringBuilder builder = new StringBuilder();
        builder.append("Auto brand: ").append(auto.getVehicleBrand()).append(";\n");
        var names = auto.getModelNames();
        builder.append("Auto models: [\n");
        Arrays.stream(names).forEachOrdered(value -> {
            builder.append("{ name: ").append(value);
            try {
                var price = auto.getModelPriceByName(value);
                builder.append(", price: ").append(price);
            } catch (NoSuchModelNameException e) {
                e.printStackTrace();
            }
            builder.append(" },\n");
        });
        builder.append("];\n");

        return builder.toString();
    }
}
