package tr.com.vodafone.garageservice.application.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

@Component
public class GarageManagerServiceImpl implements GarageManagerService {

    ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("scopes.xml");
    private Garage garage = (Garage) applicationContext.getBean("garageSingleton");

    @Override
    public boolean parkVehicle(VehicleDto vehicleDto) {
        if(garage.getGarageVacancy()+vehicleDto.getVehicleType().getSlot()>=10)
            return false;
        int index=garage.isAvaliable(vehicleDto.getVehicleType().getSlot());
        garage.getVehicles().add(Vehicle.builder()
                .slotsize(vehicleDto.getVehicleType())
                .numberPlate(vehicleDto.getNumberPlate())
                .color(vehicleDto.getColor())
                .id(index)
                .build());
        garage.setGarageVacancy(garage.getGarageVacancy()+vehicleDto.getVehicleType().getSlot()+1);
        return true;
    }

    @Override
    public VehicleDto leaveVehicle(int id){
        Vehicle vehicle=garage.getVehicles().stream().filter(v -> v.getId()==id).findFirst().map(v -> {
            garage.getVehicles().remove(v);
            garage.setGarageVacancy(garage.getGarageVacancy()-v.getSlotsize().getSlot()-1);
            return v;
        }).orElseThrow();
        return VehicleDto.builder()
                .color(vehicle.getColor())
                .numberPlate(vehicle.getNumberPlate())
                .vehicleType(vehicle.getSlotsize())
                .build();

    }




}
