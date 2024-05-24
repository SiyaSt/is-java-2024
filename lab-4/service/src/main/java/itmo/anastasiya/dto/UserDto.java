package itmo.anastasiya.dto;

import itmo.anastasiya.entity.Role;
import lombok.Builder;
import lombok.Value;

/**
 * DTO for {@link itmo.anastasiya.entity.User}
 */
@Value
@Builder
public class UserDto {
    Long id;
    String username;
    String password;
    Role role;
    Long ownerId;
}
