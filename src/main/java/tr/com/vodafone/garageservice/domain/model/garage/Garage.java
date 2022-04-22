package tr.com.vodafone.garageservice.domain.model.garage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import tr.com.vodafone.garageservice.domain.model.vehicle.Vehicle;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Vector;

@Getter
@Setter
public class Garage {
    @Size(max = 10)
    private List<Vehicle> vehicles;

    public Garage() {
        vehicles = new Vector<>(10);
    }

    @Bean
    @Scope("singleton")
    public Garage garageSingleton() {
        return new Garage();
    }
}


