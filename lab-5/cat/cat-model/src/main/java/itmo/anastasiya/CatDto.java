package itmo.anastasiya;

import lombok.Builder;

import java.util.Date;


@Builder
public record CatDto(Long id, String name, Date birthdayDate, String breed, String color, Long ownerId) {
}