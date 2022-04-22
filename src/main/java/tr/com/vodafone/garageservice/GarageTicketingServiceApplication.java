package tr.com.vodafone.garageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class GarageTicketingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GarageTicketingServiceApplication.class, args);
    }

    @Bean
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Europe/Istanbul")));
    }
}
