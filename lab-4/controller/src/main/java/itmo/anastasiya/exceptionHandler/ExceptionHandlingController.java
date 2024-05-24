package itmo.anastasiya.exceptionHandler;

import itmo.anastasiya.exceptions.cat.CatServiceException;
import itmo.anastasiya.exceptions.owner.OwnerServiceException;
import itmo.anastasiya.exceptions.user.UserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(OwnerServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleOwnerServiceException(WebRequest request, OwnerServiceException ownerServiceException) {
        return ErrorMessage
                .builder()
                .message(ownerServiceException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .build();

    }

    @ExceptionHandler(CatServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleCatServiceException(WebRequest request, CatServiceException catServiceException) {
        return ErrorMessage
                .builder()
                .message(catServiceException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .build();

    }

    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserServiceException(WebRequest request, UserServiceException userServiceException) {
        return ErrorMessage
                .builder()
                .message(userServiceException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .build();

    }

}
