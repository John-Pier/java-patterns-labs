package models.vehicle;

import java.io.Serializable;
import java.util.*;

public class Car implements Vehicle {
    private String carBrand;
    private Model[] models;

    public Car(String carBrand, int sizeOfModels) {
        this.carBrand = carBrand;
        this.models = new Model[sizeOfModels];
    }

    public String getVehicleBrand() {
        return this.carBrand;
    }

    public void setVehicleBrand(String carModel) {
        this.carBrand = carModel;
    }

    public void setNameByName(String name, String newName) throws Exception {
        if (isModelNameExist(newName)) {
            throw new Exception("Duplicate model name!");
        }
        for (Model model : this.models) {
            if (model != null && model.name.equals(name)) {
                model.name = newName;
                return;
            }
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    public String[] getModelNames() {
        return Arrays.stream(this.models).filter(Objects::nonNull).map(model -> model.name).toArray(String[]::new);
    }

    public double getModelPriceByName(String name) throws Exception {
        return Arrays
                .stream(this.models)
                .filter(model -> model != null && model.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new Exception("Model with name '" + name + "' not found!"))
                .price;
    }

    public void setModelPriceByName(String name, double price) throws Exception {
        if (price < 0) {
            throw new Exception("Model Price Out Of Bounds!");
        }
        for (Model model : this.models) {
            if (model != null && model.name.equals(name)) {
                model.price = price;
                return;
            }
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    public double[] getModelPrices() {
        return Arrays.stream(this.models).filter(Objects::nonNull).mapToDouble(model -> model.price).toArray();
    }

    public void addModel(String name, double price) throws Exception {
        if (price < 0) {
            throw new Exception("Model Price Out Of Bounds!");
        }
        if (isModelNameExist(name)) {
            throw new Exception("Duplicate model name!");
        }
        Model newModel = new Model(name, price);
        int index = getIndexOfNullElement();
        if (index == this.models.length) {
            this.models = Arrays.copyOf(this.models, this.models.length + 1);
        }
        this.models[index] = newModel;
    }

    private boolean isModelNameExist(String name) {
        return Arrays
                .stream(this.models)
                .anyMatch(model -> model != null && model.name.equals(name));
    }

    private int getIndexOfNullElement() {
        for (int i = 0; i < this.models.length; i++) {
            if (this.models[i] == null) {
                return i;
            }
        }
        return this.models.length;
    }

    public void deleteModel(String name) throws Exception {
        for (int i = 0; i < this.models.length; i++) {
            if (this.models[i] != null && this.models[i].name.equals(name)) {
                if (i != this.models.length - 1) {
                    System.arraycopy(this.models, ++i, this.models, --i, this.models.length - 1 - i);
                }
                this.models = Arrays.copyOf(this.models, this.models.length - 1);
                return;
            }
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    public int getModelsSize() {
        return this.models.length;
    }

    @Override
    public String getVehicleClassName() {
        return Car.class.getSimpleName();
    }

    private class Model implements Serializable {
        private String name;
        private double price;

        public Model() {
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
