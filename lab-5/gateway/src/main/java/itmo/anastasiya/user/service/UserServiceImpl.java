package itmo.anastasiya.user.service;

import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.client.OwnerClient;
import itmo.anastasiya.service.exceptions.UserServiceException;
import itmo.anastasiya.service.map.MappingUtils;
import itmo.anastasiya.user.dto.UserDto;
import itmo.anastasiya.user.entity.Role;
import itmo.anastasiya.user.entity.User;
import itmo.anastasiya.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final KafkaTemplate<String, Long> deleteOwner;
    private final UserRepository userRepository;
    private final OwnerClient ownerClient;

    @Transactional
    public UserDto save(String username, String password, Role role, Long ownerId) {
        Long owner = ownerClient.getOne(ownerId).id();
        if (userRepository.findByOwner(owner) != null) {
            throw UserServiceException.userAlreadyExists(ownerId);
        }
        User user = new User(username, encoder().encode(password), role, ownerId);
        userRepository.save(user);
        return MappingUtils.mapToUserDto(user);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw UserServiceException.noSuchUser(username);
        }
        return MappingUtils.mapToUserDto(user);
    }

    public void delete(String name) {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw UserServiceException.noSuchUser(name);
        }
        OwnerDto ownerDto = ownerClient.getOne(user.getOwner());
        deleteOwner.send("delete_owner", ownerDto.id());
        userRepository.delete(user);
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
