package itmo.anastasiya.exceptionHandler;

import itmo.anastasiya.exceptions.CatServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(CatServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handle(WebRequest request, CatServiceException catServiceException) {
        return ErrorMessage
                .builder()
                .message(catServiceException.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .description(request.getDescription(false))
                .build();

    }
}
