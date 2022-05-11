package models.factory;

import models.SerializedVehicleDAO;

public class SerializedFileDaoFactory implements DaoFactory<SerializedVehicleDAO> {
    @Override
    public SerializedVehicleDAO createDao(String path) {
        return new SerializedVehicleDAO(path);
    }
}
