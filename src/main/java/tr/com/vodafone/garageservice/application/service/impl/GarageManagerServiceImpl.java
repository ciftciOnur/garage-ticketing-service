package tr.com.vodafone.garageservice.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;
import tr.com.vodafone.garageservice.domain.model.vehicle.VehicleType;
import tr.com.vodafone.garageservice.infrastructure.config.GarageServiceException;
import tr.com.vodafone.garageservice.infrastructure.config.SingletonBeanConfig;
import tr.com.vodafone.garageservice.infrastructure.config.VehicleNotFoundException;
import tr.com.vodafone.garageservice.interfaces.dto.StatusDto;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class GarageManagerServiceImpl implements GarageManagerService {

    private final Garage garage;

    @Override
    public Integer parkVehicle(VehicleDto vehicleDto) {
        VehicleType.validate(vehicleDto.getVehicleType());
        if(garage.getGarageVacancy()+vehicleDto.getVehicleType().getSlot()>10)
            throw new GarageServiceException("No vacant slot available");
        int index=garage.isAvaliable(vehicleDto.getVehicleType().getSlot());
        garage.getVehicles().put(index,Vehicle.builder()
                .slotSize(vehicleDto.getVehicleType())
                .numberPlate(vehicleDto.getNumberPlate())
                .color(vehicleDto.getColor())
                .id(index)
                .build());
        garage.setGarageVacancy(garage.getGarageVacancy()+vehicleDto.getVehicleType().getSlot()+1);
        log.info("The vehicle has been parked ", vehicleDto);
        return vehicleDto.getVehicleType().getSlot();
    }

    @Override
    public VehicleDto leaveVehicle(int id){
        Vehicle vehicle=garage.getVehicles().entrySet().stream().filter(v -> v.getKey()==id).findFirst().map(v -> {
            garage.getVehicles().remove(v.getKey(),v.getValue());
            garage.setGarageVacancy(garage.getGarageVacancy()-v.getValue().getSlotSize().getSlot()-1);
            return v.getValue();
        }).orElseThrow(() ->new VehicleNotFoundException("Vehicle not found"));
        VehicleDto vehicleDto= VehicleDto.builder()
                .color(vehicle.getColor())
                .numberPlate(vehicle.getNumberPlate())
                .vehicleType(vehicle.getSlotSize())
                .build();
        log.info("The vehicle has been taken ", vehicleDto);
        return vehicleDto;
    }

    @Override
    public List<StatusDto> status(){
        return garage.getVehicles().values().stream().map(m -> StatusDto.builder()
                .color(m.getColor())
                .numberPlate(m.getNumberPlate())
                .slots(m.getPosition())
                .build()).collect(Collectors.toList());
    }




}
