package itmo.anastasiya.user;

import itmo.anastasiya.dto.UserDto;
import itmo.anastasiya.entity.Role;

public interface UserService {
    UserDto save(String username, String password, Role role, Long ownerId);

    UserDto findByUsername(String username);

    void delete(long ownerId);
}
