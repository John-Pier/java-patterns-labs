package models;

import factory_method.models.Vehicle;

import java.io.IOException;

public interface WriteHandler {
    void write(Vehicle vehicle) throws IOException;

    void setHandler(WriteHandler handler);
}
