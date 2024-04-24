package itmo.anastasiya.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

/**
 * DTO for {@link itmo.anastasiya.entity.Owner}
 */
@Value
@Builder
public class OwnerDto {
    Long id;
    String name;
    Date birthdayDate;
}