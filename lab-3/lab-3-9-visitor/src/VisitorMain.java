import models.vehicle.*;
import models.visitor.PrintVisitor;

public class VisitorMain {
    public static void main(String[] args) throws Exception {
        var visitor = new PrintVisitor();

        var car = new Car("BMW", 3);
        car.addModel("Model 1", 534);
        car.addModel("Model 2", 41);
        car.addModel("Model 3", 973);

        var moto = new Motorcycle("Yamaha", 2);
        moto.addModel("Model 1", 124);
        moto.addModel("Model 2", 785);

        System.out.println("Test row for car:");
        car.accept(visitor);
        
        System.out.println("Test column for moto:");
        moto.accept(visitor);
    }
}
