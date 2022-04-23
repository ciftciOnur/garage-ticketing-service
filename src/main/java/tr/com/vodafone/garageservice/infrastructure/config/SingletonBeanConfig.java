package tr.com.vodafone.garageservice.infrastructure.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tr.com.vodafone.garageservice.domain.model.garage.Garage;

@Configuration
public class SingletonBeanConfig {
    @Bean("garage")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Garage getGarage() {
        return new Garage();
    }
}
