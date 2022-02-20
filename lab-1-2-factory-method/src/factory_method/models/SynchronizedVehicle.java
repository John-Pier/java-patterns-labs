package factory_method.models;

import factory_method.exeptions.*;

public class SynchronizedVehicle implements Vehicle {
    private final Vehicle vehicle;

    public SynchronizedVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String getVehicleBrand() {
        return this.vehicle.getVehicleBrand();
    }

    @Override
    public synchronized void setVehicleBrand(String carModel) {
        this.vehicle.setVehicleBrand(carModel);
    }

    @Override
    public synchronized void setNameByName(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        this.vehicle.setNameByName(name, newName);
    }

    @Override
    public String[] getModelNames() {
        return this.vehicle.getModelNames();
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        return this.vehicle.getModelPriceByName(name);
    }

    @Override
    public synchronized void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        this.vehicle.setModelPriceByName(name, price);
    }

    @Override
    public double[] getModelPrices() {
        return this.vehicle.getModelPrices();
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException {
        this.vehicle.addModel(name, price);
    }

    @Override
    public synchronized void deleteModel(String name) throws NoSuchModelNameException {
        this.vehicle.deleteModel(name);
    }

    @Override
    public int getModelsSize() {
        return this.vehicle.getModelsSize();
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return new SynchronizedVehicle((Vehicle) this.vehicle.clone());
    }
}
