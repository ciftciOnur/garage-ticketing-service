package tr.com.vodafone.garageservice.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tr.com.vodafone.garageservice.GarageTicketingServiceTestConfig;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.vehicle.VehicleType;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GarageTicketingServiceTestConfig.class)
@ActiveProfiles("default")
public class GarageManagerServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private GarageManagerService garageManagerService;

    @Test
    public void vehicleParkTest(){
        VehicleDto vehicleDto = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.CAR)
                .build();
        assertEquals(0, garageManagerService.parkVehicle(vehicleDto));
    }

    @Test
    public void vehicleStatusTest(){
        assertNotNull(garageManagerService.status());
    }

    @Test
    public void vehicleLeaveTest(){
        assertNotNull(garageManagerService.leaveVehicle(0));
    }
}

