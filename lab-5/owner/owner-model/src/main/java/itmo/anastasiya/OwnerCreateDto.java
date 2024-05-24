package itmo.anastasiya;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.util.Date;

@Builder
public record OwnerCreateDto(
        @NotBlank(message = "Name should not be blank") String name,
        @PastOrPresent(message = "Date is invalid") Date birthdayDate) {
}
