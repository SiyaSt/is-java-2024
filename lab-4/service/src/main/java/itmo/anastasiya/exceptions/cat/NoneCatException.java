package itmo.anastasiya.exceptions.cat;

public class NoneCatException extends CatServiceException {
    public NoneCatException(String message) {
        super(message);
    }

    public static CatServiceException noSuchCat(long id) {
        return new CatServiceException("No such cat with id " + id);
    }
}
