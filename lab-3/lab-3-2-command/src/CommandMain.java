import factory_method.exeptions.DuplicateModelNameException;
import models.*;

import java.io.*;

public class CommandMain {
    public static void main(String[] args) throws DuplicateModelNameException {
        var auto = new Auto("test br", 3);
        auto.addModel("Model 1", 23);
        auto.addModel("Model 2", 543);
        auto.addModel("Model 3", 73);

        var rowCommand = new RowCommand(auto);
        var columnCommand = new ColumnCommand(auto);

        System.out.println("Test rowCommand");
        auto.setPrintCommand(rowCommand);
        testCommand(auto);
        System.out.println("Test columnCommand");
        auto.setPrintCommand(columnCommand);
        testCommand(auto);
    }

    private static void testCommand(Auto auto) {
        try (var writer = new FileWriter("out/auto-command.txt", true)) {
            auto.print(writer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
