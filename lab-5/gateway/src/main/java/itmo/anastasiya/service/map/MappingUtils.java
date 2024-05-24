package itmo.anastasiya.service.map;

import itmo.anastasiya.user.dto.UserDto;
import itmo.anastasiya.user.entity.User;

public class MappingUtils {
    public static UserDto mapToUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(user.getRole().name())
                .ownerId(user.getOwner())
                .build();
    }
}
