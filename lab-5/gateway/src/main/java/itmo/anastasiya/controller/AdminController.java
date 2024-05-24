package itmo.anastasiya.controller;

import itmo.anastasiya.CatCreateDto;
import itmo.anastasiya.CatDto;
import itmo.anastasiya.FriendsDto;
import itmo.anastasiya.OwnerCreateDto;
import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.service.GatewayServiceAdmin;
import itmo.anastasiya.service.GatewayServiceMutable;
import itmo.anastasiya.user.dto.UserDto;
import itmo.anastasiya.user.entity.Role;
import itmo.anastasiya.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final GatewayServiceMutable gatewayServiceMutable;
    private final GatewayServiceAdmin gatewayServiceAdmin;

    @Autowired
    public AdminController(UserService userService, GatewayServiceMutable gatewayServiceMutable, GatewayServiceAdmin gatewayServiceAdmin) {
        this.userService = userService;
        this.gatewayServiceMutable = gatewayServiceMutable;
        this.gatewayServiceAdmin = gatewayServiceAdmin;
    }

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService
                .save(
                        userDto.getUsername(),
                        userDto.getPassword(),
                        Role.fromString(userDto.getRole()),
                        userDto.getOwnerId()
                );
    }

    @PostMapping("/cat")
    public void saveCat(@RequestBody CatCreateDto catDto) {
        gatewayServiceMutable.saveCat(catDto);
    }


    @DeleteMapping("/cat/{id}")
    public void deleteCat(@PathVariable Long id) {
        gatewayServiceMutable.deleteCat(id);
    }


    @PutMapping("cat/{id}/add-friend")
    public void addCatsFriend(@PathVariable(value = "id") Long id, @RequestParam(value = "id") Long friendId) {
        gatewayServiceMutable.addFriend(FriendsDto.builder().firstFriend(id).secondFriend(friendId).build());
    }

    @PostMapping("/owner")
    public void saveOwner(@RequestBody OwnerCreateDto ownerDto) {
        gatewayServiceMutable.saveOwner(ownerDto);
    }

    @GetMapping("owner/{id}")
    public OwnerDto one(@PathVariable Long id) {
        return gatewayServiceAdmin.getOwner(id);
    }

    @GetMapping("/owner")
    public List<OwnerDto> all() {
        return gatewayServiceAdmin.allOwners();
    }

    @DeleteMapping("owner/{id}")
    public void deleteOwner(@PathVariable Long id) {
        gatewayServiceMutable.deleteOwner(id);
    }

    @GetMapping("/cat/{id}")
    public CatDto getOneCat(@PathVariable Long id) {
        return gatewayServiceAdmin.getCatAdmin(id);
    }

    @GetMapping("/cats")
    public List<CatDto> getAllCats() {
        return gatewayServiceAdmin.getAllCatsAdmin();
    }

    @GetMapping("cat/{id}/friends")
    public List<CatDto> getCatsFriends(@PathVariable Long id) {
        return gatewayServiceAdmin.getCatsFriendsAdmin(id);
    }

    @GetMapping("/filter")
    public List<CatDto> filter(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "breed", required = false) String breed) {
        return gatewayServiceAdmin.filterAdmin(color, breed);
    }
}
