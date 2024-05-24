package itmo.anastasiya.controller.exceptionHandler;

import itmo.anastasiya.service.exceptions.CatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(CatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleCatServiceException(WebRequest request, CatException catException) {
        return ErrorMessage
                .builder()
                .message(catException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .build();

    }

}