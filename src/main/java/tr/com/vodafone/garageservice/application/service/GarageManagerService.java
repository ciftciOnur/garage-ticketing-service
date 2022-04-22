package tr.com.vodafone.garageservice.application.service;

import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

public interface GarageManagerService {
    boolean parkVehicle(VehicleDto vehicleDto);
}
