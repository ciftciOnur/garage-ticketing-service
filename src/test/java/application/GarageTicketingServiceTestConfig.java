package application;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tr.com.vodafone.garageservice.application.service.impl.GarageManagerServiceImpl;

@SpringBootTest
@Import(value = GarageManagerServiceImpl.class)
public class GarageTicketingServiceTestConfig {

}