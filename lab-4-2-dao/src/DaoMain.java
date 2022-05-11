import models.AbstractDataAccessObject;
import models.factory.*;
import models.vehicle.*;

public class DaoMain {

    private static DaoFactory serializedFileDaoFactory = new SerializedFileDaoFactory();
    private static DaoFactory textFileDaoFactory = new TextFileDaoFactory();

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            return;
        }

        var path1 = args[0];
        var path2 = args[1];

        testDao(path1, path2);
        //createFiles(path1, path2);
    }

    private static void testDao(String path1, String path2) throws Exception {

        var serializedDao = serializedFileDaoFactory.createDao(path1);
        var textDao = textFileDaoFactory.createDao(path2);

        var vehicleOne = serializedDao.readVehicle();
        var vehicleTwo = textDao.readVehicle();

        System.out.println("Read: ");
        VehicleHelper.printVehicleInPrintStream(vehicleOne, System.out);
        VehicleHelper.printVehicleInPrintStream(vehicleTwo, System.out);

        System.out.println("Change...");
        vehicleOne.setModelPriceByName("model 1", Math.random() * 1000);
        vehicleTwo.setModelPriceByName("model 1", Math.random() * 100);

        System.out.println("Write...");
        serializedDao.writeVehicle(vehicleOne);
        textDao.writeVehicle(vehicleTwo);
    }

    private static void createFiles(String path1, String path2) throws Exception {
        var moto = new Motorcycle("Moto", 3);
        moto.addModel("model 1", 3534);
        moto.addModel("model 2", 34);
        moto.addModel("model 3", 31.92);

        var car = new Car("car", 2);
        car.addModel("model 1", 52);
        car.addModel("model 2", 82);

        var serializedDao = serializedFileDaoFactory.createDao(path1);
        var textDao = textFileDaoFactory.createDao(path2);

        serializedDao.writeVehicle(moto);
        textDao.writeVehicle(car);
    }
}
