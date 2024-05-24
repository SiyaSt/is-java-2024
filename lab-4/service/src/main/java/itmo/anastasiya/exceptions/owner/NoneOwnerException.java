package itmo.anastasiya.exceptions.owner;

public class NoneOwnerException extends OwnerServiceException {
    public NoneOwnerException(String message) {
        super(message);
    }

    public static OwnerServiceException noSuchOwner(long id) {
        return new NoneOwnerException("No such owner with id " + id);
    }
}
