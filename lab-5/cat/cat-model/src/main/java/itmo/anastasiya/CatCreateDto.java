package itmo.anastasiya;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.util.Date;

@Builder
public record CatCreateDto(@NotBlank(message = "Name should not be blank") String name,
                           @PastOrPresent(message = "Date is invalid") Date birthdayDate,
                           @NotBlank(message = "Color should not be blank") String color,
                           @NotBlank(message = "Breed should not be blank") String breed, long ownerId) {
}
