package itmo.anastasiya.controller.exceptionHandler;

import itmo.anastasiya.service.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserServiceException(WebRequest request, UserException userServiceException) {
        return ErrorMessage
                .builder()
                .message(userServiceException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .build();

    }

}
