package models.vehicle;

import models.visitor.Visitor;

public interface Vehicle {

    String getVehicleBrand();

    void setVehicleBrand(String carModel);

    void setNameByName(String name, String newName) throws Exception;

    String[] getModelNames();

    double getModelPriceByName(String name) throws Exception;

    void setModelPriceByName(String name, double price) throws Exception;

    double[] getModelPrices();

    void addModel(String name, double price) throws Exception;

    void deleteModel(String name) throws Exception;

    int getModelsSize();

    void accept(Visitor visitor);
}
