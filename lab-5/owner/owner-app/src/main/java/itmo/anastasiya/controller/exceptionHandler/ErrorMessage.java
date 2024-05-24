package itmo.anastasiya.controller.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private String message;
    private HttpStatus httpStatus;
    private String description;
}