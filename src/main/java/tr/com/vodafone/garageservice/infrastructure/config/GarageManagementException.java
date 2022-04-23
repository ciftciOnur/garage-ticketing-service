package tr.com.vodafone.garageservice.infrastructure.config;

import org.springframework.http.HttpStatus;

public class GarageManagementException extends GarageServiceException{

    public GarageManagementException(String message) {
        super(HttpStatus.LOCKED.value(),message);
    }

}
