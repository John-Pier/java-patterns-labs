package models.visitor;

import models.vehicle.*;

public interface Visitor {
    void visit(Motorcycle moto);

    void visit(Car car);
}
