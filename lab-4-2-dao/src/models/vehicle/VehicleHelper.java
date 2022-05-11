package models.vehicle;

import java.io.PrintStream;
import java.util.Objects;

public class VehicleHelper {
    public static Vehicle createTypedVehicle(String className, String brandName, int modelSize) {
        if (Objects.equals(className, Car.class.getSimpleName())) {
            return new Car(brandName, modelSize);
        }
        if (Objects.equals(className, Motorcycle.class.getSimpleName())) {
            return new Motorcycle(brandName, modelSize);
        }
        return null;
    }

    public static void printVehicleInPrintStream(Vehicle vehicle, PrintStream printStream) {
        String vehicleName = vehicle.getVehicleBrand();
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();

        for (int i = 0; i < names.length; i++) {
            printStream.println(vehicleName + " [" + (i + 1) + ", name: " + names[i] + ", price: " + prices[i] + "]");
        }
    }
}
