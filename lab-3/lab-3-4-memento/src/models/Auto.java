package models;

import java.io.*;
import java.util.*;

public class Auto implements Serializable {
    private String carBrand;
    private Model[] models;

    public Auto(String carBrand, int sizeOfModels) {
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Auto brand: ").append(this.carBrand).append("; ");
        var models = this.models;
        builder.append("Auto models: [");
        Arrays.stream(models).forEachOrdered(value -> {
            builder.append("{ name: ")
                    .append(value.name)
                    .append(", price: ")
                    .append(value.price)
                    .append(" }, ");
        });
        builder.append("];\n");

        return builder.toString();
    }

    public AutoMemento createMemento() {
        var memento = new AutoMemento();
        memento.setAuto(this);
        return memento;
    }

    public void setMemento(AutoMemento memento) {
        var auto = memento.getAuto();
        if (auto == null) {
            throw new NullPointerException("Empty memento!");
        }
        this.carBrand = auto.carBrand;
        this.models = auto.models;
    }

    static class Model implements Serializable {
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

        @Override
        public String toString() {
            return "{ name: " + this.name + ", price: " + this.price + " }";
        }
    }

    public static class AutoMemento {
        private byte[] serializedAuto = null;

        private void setAuto(Auto auto) {
            var stream = new ByteArrayOutputStream();
            try (var objectOutputStream = new ObjectOutputStream(stream)) {
                objectOutputStream.writeObject(auto);
                objectOutputStream.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            serializedAuto = stream.toByteArray();
        }

        private Auto getAuto() {
            if (serializedAuto == null) {
                return null;
            }
            Auto auto = null;

            try (var objectInputStream = new ObjectInputStream(new ByteArrayInputStream(serializedAuto))) {
                auto = (Auto) objectInputStream.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return auto;
        }
    }
}
