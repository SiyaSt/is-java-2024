package itmo.anastasiya.service.exception;

public class OwnerServiceException extends OwnerException {
    OwnerServiceException(String message) {
        super(message);
    }

    public static OwnerException noSuchOwner(Long id) {
        return new OwnerException("No such owner: " + id);
    }
}
