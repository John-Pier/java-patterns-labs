package factory_method;

import factory_method.exeptions.*;
import factory_method.fabric.MotorcycleFabric;
import factory_method.models.Vehicle;
import factory_method.untils.VehicleHelper;

public class MainFactoryMethod {
    public static void main(String[] args) {
        try {
            Vehicle carVehicle = VehicleHelper.createInstance("Super Auto Brand", 4);
            VehicleHelper.setVehicleFabric(new MotorcycleFabric());
            Vehicle motoVehicle = VehicleHelper.createInstance("Super Moto Brand", 3);

            fillVehicle(carVehicle);
            fillVehicle(motoVehicle);

            var carVehicleClone = (Vehicle)carVehicle.clone();
            carVehicleClone.addModel("----test1", 999);
            var motoVehicleClone = (Vehicle)motoVehicle.clone();
            motoVehicleClone.addModel("----test2", 999);

            System.out.println("Vehicle 1 Brand: ");
            System.out.println(carVehicle.getVehicleBrand());
            System.out.println("Vehicle 2 Brand: ");
            System.out.println(motoVehicle.getVehicleBrand());

            System.out.println("Vehicle carVehicle: ");
            printVehicle(carVehicle);
            System.out.println("Vehicle carVehicle cloned: ");
            printVehicle(carVehicleClone);

            System.out.println("Vehicle motoVehicle: ");
            printVehicle(motoVehicle);
            System.out.println("Vehicle motoVehicle cloned: ");
            printVehicle(motoVehicleClone);

            System.out.println("Print vehicle using VehicleHelper");
            System.out.println("Auto: ");
            VehicleHelper.printModelsInPrintStream(carVehicle, System.out);
            System.out.println("Auto cloned: ");
            VehicleHelper.printModelsInPrintStream(carVehicleClone, System.out);
            System.out.println("Moto: ");
            VehicleHelper.printModelsInPrintStream(motoVehicle, System.out);
            System.out.println("Moto cloned: ");
            VehicleHelper.printModelsInPrintStream(motoVehicleClone, System.out);
        } catch (CloneNotSupportedException | DuplicateModelNameException | ModelPriceOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fillVehicle(Vehicle vehicle) throws DuplicateModelNameException {
        vehicle.addModel("3-T", 123);
        vehicle.addModel("Yamaha", 9878);
        vehicle.addModel("BMW", 13532);
        vehicle.addModel("AUDI", 111.9);
        vehicle.addModel("ИЖ (Рус)", 391.92);
    }

    private static void printVehicle(Vehicle vehicle) {
        System.out.println("Vehicle: " + vehicle.getVehicleBrand());
        System.out.println("Vehicle Names:");
        printNames(vehicle);
        System.out.println("vehicle Prices:");
        printPrices(vehicle);
        System.out.println("vehicle Models Size:");
        printSizeModels(vehicle);
    }

    private static void printNames(Vehicle vehicle) {
        for (String name : vehicle.getModelNames()) {
            System.out.println(name);
        }
    }

    private static void printPrices(Vehicle vehicle) {
        for (double price : vehicle.getModelPrices()) {
            System.out.println(price);
        }
    }

    public static void printSizeModels(Vehicle vehicle) {
        System.out.println(vehicle.getModelsSize());
    }
}
