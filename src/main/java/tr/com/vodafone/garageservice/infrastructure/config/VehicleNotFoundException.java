package tr.com.vodafone.garageservice.infrastructure.config;

import org.springframework.http.HttpStatus;

public class VehicleNotFoundException extends GarageServiceException{

    public VehicleNotFoundException(String message){
        super(HttpStatus.NOT_FOUND.value(),message);
    }
}
