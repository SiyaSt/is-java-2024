package itmo.anastasiya.dto;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.entity.Owner;
import lombok.Value;

import java.util.Date;
import java.util.List;

/**
 * DTO for {@link itmo.anastasiya.entity.Cat}
 */
@Value
public class CatDto {
    Long id;
    String name;
    Date birthdayDate;
    String breed;
    Color color;
    Owner owner;
    List<Cat> friends;
}