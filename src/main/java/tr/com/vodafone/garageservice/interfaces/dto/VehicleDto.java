package tr.com.vodafone.garageservice.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.vodafone.garageservice.domain.model.vehicle.VehicleType;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VehicleDto {
    private String numberPlate;
    private VehicleType vehicleType;
    private String color;

}
