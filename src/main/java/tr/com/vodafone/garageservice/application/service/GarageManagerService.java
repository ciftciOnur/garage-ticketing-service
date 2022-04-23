package tr.com.vodafone.garageservice.application.service;

import tr.com.vodafone.garageservice.interfaces.dto.StatusDto;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import java.util.List;

public interface GarageManagerService {
    boolean parkVehicle(VehicleDto vehicleDto);

    VehicleDto leaveVehicle(int id);

    List<StatusDto> status();
}
