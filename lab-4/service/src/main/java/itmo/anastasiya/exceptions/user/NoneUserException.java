package itmo.anastasiya.exceptions.user;

public class NoneUserException extends UserServiceException {
    public NoneUserException(String message) {
        super(message);
    }

    public static UserServiceException noSuchUser(String username) {
        return new NoneUserException("No such user exists with name " + username);
    }
}
