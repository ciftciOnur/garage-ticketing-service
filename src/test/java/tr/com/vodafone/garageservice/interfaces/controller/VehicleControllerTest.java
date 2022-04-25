package tr.com.vodafone.garageservice.interfaces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tr.com.vodafone.garageservice.application.service.GarageManagerService;
import tr.com.vodafone.garageservice.domain.model.vehicle.VehicleType;
import tr.com.vodafone.garageservice.interfaces.dto.VehicleDto;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @MockBean
    private GarageManagerService garageManagerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void vehicleControllerParkTest () throws Exception{
        VehicleDto truckDto = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.TRUCK)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString((truckDto)))
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void vehicleControllerLeaveTest() throws Exception{

        VehicleDto vehicle = VehicleDto.builder()
                .numberPlate("34abc123")
                .color("Blue")
                .vehicleType(VehicleType.CAR)
                .build();
        Mockito.when(garageManagerService.leaveVehicle(0)).thenReturn(vehicle);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/vehicles/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("Blue"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberPlate").value("34abc123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleType").value("CAR"));

    }
}
