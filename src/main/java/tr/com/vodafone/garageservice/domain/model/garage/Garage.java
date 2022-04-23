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

@Getter
@Setter
public class Garage {
    @Size(max = 10)
    private List<Vehicle> vehicles;
    private int garageVacancy;

    public Garage() {
        vehicles = new Vector<>(10);
        garageVacancy=0;
    }

    public int isAvaliable(int slotSize){
        int vacancy=0;
        int index=0;
        int vehicleSize=0;
        for(Vehicle vehicle:vehicles){
            index=vehicle.getId();
            vehicleSize=vehicle.getSlotsize().getSlot()+1;
            if(vacancy+slotSize<vehicle.getId()){
                return vacancy;
            }
            else{
                vacancy = vehicle.getSlotsize().getSlot() +1;
            }
        }
        return index+vehicleSize;
    }

    @Bean
    @Scope("singleton")
    public Garage garageSingleton() {
        return new Garage();
    }
}


