package tr.com.vodafone.garageservice.application.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;
import tr.com.vodafone.garageservice.domain.model.vehicle.VehicleType;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

@Component
public class GarageManagerServiceImpl implements GarageManagerService {

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("scopes.xml");
    private Garage garage = (Garage) applicationContext.getBean("garageSingleton");

    @Override
    public boolean parkVehicle(VehicleDto vehicleDto) {
        garage.getVehicles().add(Vehicle.builder()
                .slotsize(vehicleDto.getVehicleType())
                .numberPlate(vehicleDto.getNumberPlate())
                .color(vehicleDto.getColor())
                .build());
        return true;
    }




}
