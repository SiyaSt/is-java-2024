package itmo.anastasiya.exceptions.user;

public class ExtraUserException extends UserServiceException {
    public ExtraUserException(String message) {
        super(message);
    }

    public static UserServiceException userAlreadyExists(long id) {
        return new UserServiceException("User already exists with such owner: " + id);
    }
}
