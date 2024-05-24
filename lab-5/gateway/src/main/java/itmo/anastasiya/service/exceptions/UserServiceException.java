package itmo.anastasiya.service.exceptions;

public class UserServiceException extends UserException {
    UserServiceException(String message) {
        super(message);
    }

    public static UserException userAlreadyExists(long id) {
        return new UserServiceException("User already exists with such owner: " + id);
    }

    public static UserException accessDenied() {
        return new UserServiceException("Access denied");
    }

    public static UserException noSuchUser(String username) {
        return new UserServiceException("No such user exists with name " + username);
    }
}
