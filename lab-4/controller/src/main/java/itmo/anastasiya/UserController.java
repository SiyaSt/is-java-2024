package itmo.anastasiya;

import itmo.anastasiya.dto.UserDto;
import itmo.anastasiya.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService
                .save(
                        userDto.getUsername(),
                        userDto.getPassword(),
                        userDto.getRole(),
                        userDto.getOwnerId()
                );
    }
}
