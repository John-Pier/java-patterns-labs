package models;

import models.vehicle.Vehicle;

public abstract class AbstractDataAccessObject implements DataAccessObject {

    protected String pathToFile;

    public AbstractDataAccessObject(String path) {
        pathToFile = path;
    }

    public abstract void writeVehicle(Vehicle vehicle) throws Exception;

    public abstract Vehicle readVehicle() throws Exception;
}
