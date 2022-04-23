package tr.com.vodafone.garageservice.domain.model.garage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
public class Garage {
    @Size(max = 10)
    private ConcurrentMap<Integer,Vehicle> vehicles;
    private int garageVacancy;

    public Garage() {
        vehicles = new ConcurrentHashMap<>(11); //imaginary +1 slot to compansate last car's right buffer.
        garageVacancy=0;
    }

    public int isAvaliable(int slotSize){
        int index=0;
        int vehicleSize=0;
        int prevIndex=0;
        for(Vehicle vehicle:vehicles.values()){
            index=vehicle.getId();
            vehicleSize=vehicle.getSlotsize().getSlot()+1;
            if(prevIndex+slotSize<vehicle.getId()){
                return prevIndex;
            }
            else{
                prevIndex = vehicle.getId()+vehicle.getSlotsize().getSlot() +1;
            }
        }
        return index+vehicleSize;
    }

}


