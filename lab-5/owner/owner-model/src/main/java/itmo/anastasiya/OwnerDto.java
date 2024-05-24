package itmo.anastasiya;

import lombok.Builder;

import java.util.Date;

@Builder
public record OwnerDto(Long id, String name, Date birthdayDate) {
}
