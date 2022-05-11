package models.vehicle;

import java.io.Serializable;

public interface Vehicle extends Serializable {

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

    String getVehicleClassName();
}
