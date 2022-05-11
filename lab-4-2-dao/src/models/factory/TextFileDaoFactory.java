package models.factory;

import models.TextVehicleDAO;

public class TextFileDaoFactory implements DaoFactory<TextVehicleDAO> {
    @Override
    public TextVehicleDAO createDao(String path) {
        return new TextVehicleDAO(path);
    }
}
