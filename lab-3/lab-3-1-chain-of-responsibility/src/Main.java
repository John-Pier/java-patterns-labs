import factory_method.exeptions.DuplicateModelNameException;
import factory_method.untils.VehicleHelper;
import models.*;

import java.io.*;

public class Main {
    public static WriteHandler firsHandler;

    public static void main(String[] args) throws IOException, DuplicateModelNameException {
        initializeChain();
        testHandlers();
    }

    private static void initializeChain() throws IOException {
        var writer = new FileWriter("out/vehicle.txt");
        firsHandler = new RowWriteHandler(writer);
        firsHandler.setHandler(new ColumnWriteHandler(writer));
    }

    private static void testHandlers() throws DuplicateModelNameException, IOException {
        var vehicle = VehicleHelper.createInstance("test brand", 3);
        if (vehicle == null) {
            return;
        }
        vehicle.addModel("model1", 980);
        vehicle.addModel("model2", 660);
        vehicle.addModel("model3", 999);

        firsHandler.write(vehicle);

        vehicle.addModel("model4", 981);
        firsHandler.write(vehicle);
    }
}
