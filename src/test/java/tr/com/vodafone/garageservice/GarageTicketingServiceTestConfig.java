package tr.com.vodafone.garageservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.vodafone.garageservice.application.service.impl.GarageManagerServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(value = GarageManagerServiceImpl.class)
public class GarageTicketingServiceTestConfig {

}