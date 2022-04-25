package tr.com.vodafone.garageservice.domain.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tr.com.vodafone.garageservice.infrastructure.config.VehicleNotFoundException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum VehicleType {
    CAR(1),
    JEEP(2),
    TRUCK(4);

    private final int slot;

    public static void validate(VehicleType vehicleType){
        if(Arrays.asList(VehicleType.values()).stream().noneMatch(m-> m.equals(vehicleType))){
            throw new VehicleNotFoundException("Vehicle type not found");
        }
    }
}
