package tr.com.vodafone.garageservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GarageTicketingServiceApplicationTest {

    @Test
    void contextLoads() {
    }
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Garage garage() {
        return new Garage();
    }
}