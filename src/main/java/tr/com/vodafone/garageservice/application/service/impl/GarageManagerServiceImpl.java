package tr.com.vodafone.garageservice.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;
import tr.com.vodafone.garageservice.infrastructure.config.SingletonBeanConfig;
import tr.com.vodafone.garageservice.interfaces.dto.StatusDto;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GarageManagerServiceImpl implements GarageManagerService {

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(SingletonBeanConfig.class);
    Garage garage = (Garage) context.getBean("garage");

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

    @Override
    public List<StatusDto> status(){
        List<StatusDto> statusDtoList= new ArrayList<>();
       for(Vehicle vehicle : garage.getVehicles()){
           List<Integer> slots = new ArrayList<>();
           for(int i=0; i<vehicle.getSlotsize().getSlot();i++){
               slots.add(vehicle.getId()+i);
           }
           statusDtoList.add(StatusDto.builder()
                   .color(vehicle.getColor())
                   .numberPlate(vehicle.getNumberPlate())
                   .slots(slots).build());
       }
       return statusDtoList;
    }




}
