package services.tools;

public class ShelterServiceException extends Exception {
    public ShelterServiceException() {
        super();
    }

    public ShelterServiceException(String message) {
        super(message);
    }

    public ShelterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
