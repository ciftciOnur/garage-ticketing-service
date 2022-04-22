package tr.com.vodafone.garageservice.domain.model.vehicle;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private VehicleType slotsize;
    private String color;
    private String numberPlate;


}
