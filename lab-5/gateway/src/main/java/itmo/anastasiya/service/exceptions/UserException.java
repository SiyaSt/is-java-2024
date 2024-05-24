package itmo.anastasiya.service.exceptions;

public class UserException extends RuntimeException {
    UserException(String message) {
        super(message);
    }
}
