package tr.com.vodafone.garageservice.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;
import tr.com.vodafone.garageservice.interfaces.dto.StatusDto;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    GarageManagerService garageManagerService;

    @PostMapping
    public void parkVehicle(@RequestBody VehicleDto vehicleDto) {
        garageManagerService.parkVehicle(vehicleDto);
    }

    @GetMapping("/{vehicleId}")
    public VehicleDto leaveVehicle(@PathVariable int vehicleId) {
        return garageManagerService.leaveVehicle(vehicleId);
    }

    @GetMapping("/status")
    public List<StatusDto> status() {
        return garageManagerService.status();
    }


}
