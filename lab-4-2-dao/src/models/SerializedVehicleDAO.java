package models;

import models.vehicle.Vehicle;

import java.io.*;

public class SerializedVehicleDAO extends AbstractDataAccessObject {

    public SerializedVehicleDAO(String path) {
        super(path);
    }

    @Override
    public void writeVehicle(Vehicle vehicle) throws Exception {
        try (var stream = new ObjectOutputStream(new FileOutputStream(pathToFile))) {
            stream.writeObject(vehicle);
            stream.flush();
        }
    }

    @Override
    public Vehicle readVehicle() {
        Vehicle vehicle = null;
        try (var stream = new ObjectInputStream(new FileInputStream(pathToFile))) {
            return (Vehicle) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vehicle;
    }
}
