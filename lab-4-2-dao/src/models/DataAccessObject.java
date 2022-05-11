package models;

import models.vehicle.Vehicle;

public interface DataAccessObject {
    void writeVehicle(Vehicle vehicle) throws Exception;

    Vehicle readVehicle() throws Exception;
}
