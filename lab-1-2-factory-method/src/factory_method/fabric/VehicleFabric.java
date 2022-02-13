package factory_method.fabric;

import factory_method.models.Vehicle;

public interface VehicleFabric {
   Vehicle createVehicle(String brand, int sizeOfModels);
}
