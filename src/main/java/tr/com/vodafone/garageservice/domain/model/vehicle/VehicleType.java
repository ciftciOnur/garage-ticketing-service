package tr.com.vodafone.garageservice.domain.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleType {
    CAR(1),
    JEEP(2),
    TRUCK(4);

    private final int slot;

}
