package factory_method.fabric;

import factory_method.models.*;

public class CarFabric implements VehicleFabric {
    @Override
    public Vehicle createVehicle(String brand, int sizeOfModels) {
        return new Car(brand, sizeOfModels);
    }
}
