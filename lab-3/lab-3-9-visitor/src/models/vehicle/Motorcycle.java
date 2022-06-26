package models.vehicle;

import models.visitor.Visitor;

public class Motorcycle implements Vehicle {

    private String motoBrand;
    private int size;
    private final Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }

    public Motorcycle(String motoBrand, int sizeOfModels) {
        this.motoBrand = motoBrand;
        this.size = sizeOfModels;
    }

    public String getVehicleBrand() {
        return motoBrand;
    }

    public void setVehicleBrand(String model) {
        this.motoBrand = model;
    }

    public String[] getModelNames() {
        String[] names = new String[getNonNullSize()];
        Model currentModel = head;

        int count = 0;
        while (currentModel.next != head) {
            names[count] = currentModel.next.name;
            currentModel = currentModel.next;
            count++;
        }

        return names;
    }

    public double getModelPriceByName(String name) throws Exception {
        Model currentModel = head;
        while (currentModel.next != head) {
            if (currentModel.next.name.equals(name)) {
                return currentModel.next.price;
            }
            currentModel = currentModel.next;
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    public double[] getModelPrices() {
        double[] prices = new double[getNonNullSize()];
        Model currentModel = head;

        int count = 0;
        while (currentModel.next != head) {
            prices[count] = currentModel.next.price;
            currentModel = currentModel.next;
            count++;
        }

        return prices;
    }

    public void addModel(String name, double price) throws Exception {
        if (price < 0) {
            throw new Exception("Model Price Out Of Bounds!");
        }

        if (isModelNameExist(name)) {
            throw new Exception("Duplicate model name!");
        }

        Model newModel = new Model(name, price);
        Model lastModel = head.prev;

        lastModel.next = newModel;
        newModel.next = head;
        head.prev = newModel;
        newModel.prev = lastModel;
        if (getNonNullSize() == size) {
            size++;
        }
    }

    private int getNonNullSize() {
        int count = 0;
        Model currentModel = head.next;
        while (currentModel != head) {
            if (currentModel.name != null) {
                count++;
            }
            currentModel = currentModel.next;
        }
        return count;
    }

    public void setNameByName(String name, String newName) throws Exception {
        if (isModelNameExist(newName)) {
            throw new Exception("Duplicate model name!");
        }
        Model currentModel = head;
        while (currentModel.next != head) {
            if (currentModel.next.name.equals(name)) {
                currentModel.next.name = newName;
                return;
            }
            currentModel = currentModel.next;
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    private boolean isModelNameExist(String name) {
        Model currentModel = head;
        while (currentModel.next != head) {
            if (currentModel.next.name.equals(name)) {
                return true;
            }
            currentModel = currentModel.next;
        }
        return false;
    }

    public void deleteModel(String name) throws Exception {
        Model currentModel = head;
        while (currentModel.next != head) {
            if (currentModel.next.name.equals(name)) {
                currentModel.next.prev = currentModel.prev;
                currentModel.prev.next = currentModel.next;
                size--;
                return;
            }
            currentModel = currentModel.next;
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    public void setModelPriceByName(String name, double price) throws Exception {
        if (price < 0) {
            throw new Exception("Model Price Out Of Bounds!");
        }
        Model lastModel = head;
        while (lastModel.next != head) {
            if (lastModel.next.name.equals(name)) {
                lastModel.next.setPrice(price);
                return;
            }
            lastModel = lastModel.next;
        }
        throw new Exception("Model with name '" + name + "' not found!");
    }

    public int getModelsSize() {
        return this.size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    private class Model {

        private String name = null;
        private double price = Double.NaN;
        private Model prev = null;
        private Model next = null;

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Model getPrev() {
            return prev;
        }

        public void setPrev(Model prev) {
            this.prev = prev;
        }

        public Model getNext() {
            return next;
        }

        public void setNext(Model next) {
            this.next = next;
        }
    }
}
