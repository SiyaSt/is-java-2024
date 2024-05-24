package itmo.anastasiya.user;

import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.UserDto;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.entity.Role;
import itmo.anastasiya.entity.User;
import itmo.anastasiya.exceptions.owner.NoneOwnerException;
import itmo.anastasiya.exceptions.user.ExtraUserException;
import itmo.anastasiya.exceptions.user.NoneUserException;
import itmo.anastasiya.repository.OwnerRepository;
import itmo.anastasiya.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public UserDto save(String username, String password, Role role, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> NoneOwnerException.noSuchOwner(ownerId));
        if (userRepository.findByOwner(owner) != null) {
            throw ExtraUserException.userAlreadyExists(ownerId);
        }
        User user = new User(username, encoder().encode(password), role, owner);
        userRepository.save(user);
        return MappingUtils.mapToUserDto(user);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw NoneUserException.noSuchUser(username);
        }
        return MappingUtils.mapToUserDto(user);
    }

    public void delete(long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> NoneOwnerException.noSuchOwner(ownerId));
        User user = userRepository.findByOwner(owner);
        userRepository.delete(user);
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
