package models;

import factory_method.exeptions.NoSuchModelNameException;

import java.io.*;
import java.util.Arrays;

public class RowCommand implements Command {
    private final Auto auto;

    public RowCommand(Auto auto) {
        this.auto = auto;
    }

    @Override
    public void execute(Writer writer) {
        try {
            writer.write("\n");
            writer.write(toRowString(auto));
            writer.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String toRowString(Auto auto) {
        StringBuilder builder = new StringBuilder();
        builder.append("Auto brand: ").append(auto.getVehicleBrand()).append("; ");
        var names = auto.getModelNames();
        builder.append("Auto models: [");
        Arrays.stream(names).forEachOrdered(value -> {
            builder.append("{ name: ").append(value);
            try {
                var price = auto.getModelPriceByName(value);
                builder.append(", price: ").append(price);
            } catch (NoSuchModelNameException e) {
                e.printStackTrace();
            }
            builder.append(" }, ");
        });
        builder.append("];\n");

        return builder.toString();
    }
}
