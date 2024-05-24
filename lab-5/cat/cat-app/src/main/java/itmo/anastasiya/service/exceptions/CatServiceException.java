package itmo.anastasiya.service.exceptions;

public class CatServiceException extends CatException {
    public CatServiceException(String message) {
        super(message);
    }

    public static CatServiceException noSuchCat(Long id) {
        return new CatServiceException("No such cat with id: " + id);
    }

    public static CatServiceException noSuchOwner(Long id) {
        return new CatServiceException("No such owner with id: " + id);
    }

    public static CatServiceException accessDenied() {
        return new CatServiceException("Access denied");
    }
}
