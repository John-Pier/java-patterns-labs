package factory_method.untils;

import factory_method.fabric.*;
import factory_method.models.*;

import java.io.PrintStream;
import java.util.*;

public class VehicleHelper {
    private static VehicleFabric vehicleFabric = new CarFabric();

    public static void setVehicleFabric(VehicleFabric vehicleFabric) {
        VehicleHelper.vehicleFabric = vehicleFabric;
    }

    public static Vehicle createInstance(String brand, int sizeOfModels) {
        return vehicleFabric != null
                ? vehicleFabric.createVehicle(brand, sizeOfModels)
                : null;
    }

    public static double getAveragePrice(Vehicle vehicle) {
        return Arrays.stream(vehicle.getModelPrices()).average().orElse(Double.NaN);
    }

    public static void printModelsInPrintStream(Vehicle vehicle, PrintStream printStream) {
        String vehicleName = vehicle.getVehicleBrand();
        Arrays.stream(vehicle.getModelNames())
                .filter(Objects::nonNull)
                .forEachOrdered(modelName -> printStream.println(vehicleName + ": " + modelName));
    }

    public static void printModelWithPrisesInPrintStream(Vehicle vehicle, PrintStream printStream) {
        String vehicleName = vehicle.getVehicleBrand();
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();

        for (int i = 0; i < names.length; i++) {
            printStream.println(vehicleName + " [" + (i + 1) + ", name: " + names[i] + ", price: " + prices[i] + "]");
        }
    }

    public static Vehicle synchronizedVehicle(Vehicle vehicle) {
       return new SynchronizedVehicle(vehicle);
    }
}
