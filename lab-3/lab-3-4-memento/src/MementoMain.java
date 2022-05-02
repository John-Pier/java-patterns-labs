import models.Auto;

public class MementoMain {
    public static void main(String[] args) throws Exception {
        var autoForTests = new Auto("Test Br", 2);
        var editedModelName = "model 1";
        autoForTests.addModel(editedModelName, 32);
        autoForTests.addModel("model 2", 32);

        System.out.println("Auto before create memento");
        printAuto(autoForTests);

        var memento = autoForTests.createMemento();

        autoForTests.addModel("model 4", 343);
        autoForTests.setModelPriceByName(editedModelName, 9999);

        System.out.println("Auto after create memento");
        printAuto(autoForTests);

        autoForTests.setMemento(memento);

        System.out.println("Auto after apply memento");
        printAuto(autoForTests);
    }

    private static void printAuto(Auto auto) {
        System.out.println(auto.toString());
    }
}
