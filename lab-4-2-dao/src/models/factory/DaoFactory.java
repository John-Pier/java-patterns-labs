package models.factory;

import models.AbstractDataAccessObject;

public interface DaoFactory<T extends AbstractDataAccessObject> {
    T createDao(String path);
}
