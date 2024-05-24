package itmo.anastasiya.user.service;

import itmo.anastasiya.user.dto.UserDto;
import itmo.anastasiya.user.entity.Role;

public interface UserService {
    UserDto save(String username, String password, Role role, Long ownerId);

    UserDto findByUsername(String username);

    void delete(String name);
}
