package itmo.anastasiya.exceptions;

/**
 * cat service exception
 *
 * @author Anastasiya
 */
public class CatServiceException extends RuntimeException {

    public CatServiceException(String message) {
        super(message);
    }
}
