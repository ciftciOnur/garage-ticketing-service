package tr.com.vodafone.garageservice.infrastructure.config;

import org.springframework.http.HttpStatus;

public class GarageServiceException extends RuntimeException {

    private int code;

    public GarageServiceException() {
    }

    public GarageServiceException(String message) {
        super(message);
        this.code = 500;
    }

    public GarageServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GarageServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.code = httpStatus.value();
    }

    public int getCode() {
        return code;
    }
}

