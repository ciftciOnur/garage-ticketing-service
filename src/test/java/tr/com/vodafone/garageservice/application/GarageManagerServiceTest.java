package tr.com.vodafone.garageservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.vodafone.garageservice.application.service.impl.GarageManagerServiceImpl;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;
import tr.com.vodafone.garageservice.domain.model.vehicle.VehicleType;
import tr.com.vodafone.garageservice.infrastructure.config.GarageManagementException;
import tr.com.vodafone.garageservice.infrastructure.config.VehicleNotFoundException;
import tr.com.vodafone.garageservice.interfaces.dto.StatusDto;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default")
public class GarageManagerServiceTest {

    @InjectMocks
    private GarageManagerServiceImpl garageManagerService;

    @Mock
    private Garage garage;

    @BeforeAll
    public void init (){
        garage = new Garage();
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleParkTest(){
        VehicleDto vehicle1 = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.CAR)
                .build();
        VehicleDto vehicle2 = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.JEEP)
                .build();
        VehicleDto vehicle3 = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.TRUCK)
                .build();
        VehicleDto vehicle4 = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .build();
        Mockito.when(garage.getVehicles()).thenReturn(new ConcurrentHashMap<Integer,Vehicle>(10));
        assertEquals(1, garageManagerService.parkVehicle(vehicle1));
        assertEquals(2, garageManagerService.parkVehicle(vehicle2));
        assertEquals(4, garageManagerService.parkVehicle(vehicle3));
        assertEquals(VehicleNotFoundException.class, garageManagerService.parkVehicle(vehicle4));
    }

    @Test
    public void vehicleStatusTest() throws JsonProcessingException {
        Mockito.when(garage.getVehicles()).thenReturn(new ConcurrentHashMap<Integer,Vehicle>(10){{
            put(0,Vehicle.builder()
                    .slotSize(VehicleType.TRUCK)
                    .numberPlate("34abc123")
                    .color("Blue")
                    .id(0)
                    .build());}});

        assertEquals(new ObjectMapper().writeValueAsString( Arrays.asList(StatusDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .slots(Arrays.asList(0,1,2,3))
                .build())), new ObjectMapper().writeValueAsString(garageManagerService.status()));
    }

    @Test(expected = VehicleNotFoundException.class)
    public void vehicleLeaveTest() throws JsonProcessingException {
        Mockito.when(garage.getVehicles()).thenReturn(new ConcurrentHashMap<Integer,Vehicle>(11){{
                put(0,Vehicle.builder()
                        .slotSize(VehicleType.CAR)
                        .numberPlate("34abc123")
                        .color("Blue")
                        .id(0)
                        .build());}});
        assertEquals(new ObjectMapper().writeValueAsString(VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.CAR)
                .build()), new ObjectMapper().writeValueAsString(garageManagerService.leaveVehicle(0)));

        assertEquals(VehicleNotFoundException.class, garageManagerService.leaveVehicle(2));
    }
}

