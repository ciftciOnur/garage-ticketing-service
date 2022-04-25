package tr.com.vodafone.garageservice.domain.model.vehicle;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private int id;
    private VehicleType slotSize;
    private String color;
    private String numberPlate;

    public List<Integer> getPosition() {
        List<Integer> slots = new ArrayList<>();
        for (int i = 0; i < slotSize.getSlot(); i++) {
            slots.add(id + i);
        }
        return slots;
    }


}
