package tr.com.vodafone.garageservice.infrastructure.config;

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

    public int getCode() {
        return code;
    }
}

