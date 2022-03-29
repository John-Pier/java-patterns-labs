import models.Auto;

public class IteratorMain {
    public static void main(String[] args) throws Exception {
        var auto = new Auto("test", 3);
        auto.addModel("first", 32);
        auto.addModel("twe", 32);
        auto.addModel("sdfgsdf", 1987);
        auto.addModel("t34gdsf", 23);
        auto.addModel("mui,u54", 2534);

        for (var model : auto) {
            System.out.println(model);
        }
    }
}
