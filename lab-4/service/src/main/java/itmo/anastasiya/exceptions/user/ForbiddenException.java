package itmo.anastasiya.exceptions.user;

public class ForbiddenException extends UserServiceException {

    public ForbiddenException(String message) {
        super(message);
    }

    public static UserServiceException accessDenied() {
        return new ForbiddenException("Access denied");
    }
}
