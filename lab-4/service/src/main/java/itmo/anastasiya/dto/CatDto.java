package itmo.anastasiya.dto;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

/**
 * DTO for {@link Cat}
 */
@Value
@Builder
public class CatDto {
    Long id;
    String name;
    Date birthdayDate;
    String breed;
    Color color;
    Long ownerId;
}