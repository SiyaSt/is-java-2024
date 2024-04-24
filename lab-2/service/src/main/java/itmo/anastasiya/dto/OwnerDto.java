package itmo.anastasiya.dto;

import itmo.anastasiya.entity.Cat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * DTO for {@link itmo.anastasiya.entity.Owner}
 */
@Data
public class OwnerDto {
    private final Long id;
    private final String name;
    private final Date birthdayDate;
    private final List<Cat> cats;
}