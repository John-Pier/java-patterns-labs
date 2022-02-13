package factory_method.fabric;

import factory_method.models.*;

public class MotorcycleFabric implements VehicleFabric {
    @Override
    public Vehicle createVehicle(String brand, int sizeOfModels) {
        return new Motorcycle(brand, sizeOfModels);
    }
}
