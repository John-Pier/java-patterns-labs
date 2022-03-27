package models;

import factory_method.exeptions.NoSuchModelNameException;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RowCommand implements Command {
    private final Auto auto;

    public RowCommand(Auto auto) {
        this.auto = auto;
    }

    @Override
    public void execute(OutputStream stream) {
        try {
            stream.write(toRowString(auto).getBytes(StandardCharsets.UTF_8));
            stream.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String toRowString(Auto auto) {
        StringBuilder builder = new StringBuilder();
        builder.append("Vehicle brand: ").append(auto.getVehicleBrand()).append("; ");
        var names = auto.getModelNames();
        builder.append("Vehicle models: [");
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
