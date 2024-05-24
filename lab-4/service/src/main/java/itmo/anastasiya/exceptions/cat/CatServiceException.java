package itmo.anastasiya.exceptions.cat;

public class CatServiceException extends RuntimeException {
    public CatServiceException(String message) {
        super(message);
    }
}
