package models;

import models.vehicle.*;

import java.io.*;
import java.util.Arrays;

public class TextVehicleDAO extends AbstractDataAccessObject {

    public TextVehicleDAO(String path) {
        super(path);
    }

    @Override
    public void writeVehicle(Vehicle vehicle) throws Exception {
        try (var writer = new PrintWriter(new FileWriter(pathToFile))) {
            writer.println(vehicle.getVehicleClassName());
            writer.println(vehicle.getVehicleBrand());

            var names = vehicle.getModelNames();
            writer.println(names.length);

            var builder = new StringBuilder();
            Arrays.stream(names).forEachOrdered(value -> {
                try {
                    var price = vehicle.getModelPriceByName(value);
                    writer.println(value);
                    builder.append(price).append("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            writer.print(builder.toString());
            writer.flush();
        }
    }

    @Override
    public Vehicle readVehicle() {
        Vehicle vehicle = null;
        try (var reader = new BufferedReader(new FileReader(pathToFile))) {
            var className = reader.readLine();
            var brand = reader.readLine();
            var modelSize = Integer.parseInt(reader.readLine());
            vehicle = VehicleHelper.createTypedVehicle(className, brand, modelSize);

            if (vehicle == null) {
                return null;
            }

            var names = readNames(modelSize, reader);
            int j = 0;
            while (reader.ready() && j < modelSize) {
                var price = Double.parseDouble(reader.readLine());
                vehicle.addModel(names[j], price);
                j++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    private String[] readNames(int size, BufferedReader reader) throws IOException {
        var names = new String[size];
        int i = 0;
        while (reader.ready() && i < size) {
            names[i] = reader.readLine();
            i++;
        }
        return names;
    }
}
