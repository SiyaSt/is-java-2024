package itmo.anastasiya;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.user.MyUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/api/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CatDto saveCat(@RequestBody CatDto catDto) {
        return catService.save(
                catDto.getName(),
                catDto.getBirthdayDate(),
                catDto.getBreed(),
                catDto.getColor(),
                catDto.getOwnerId());
    }

    @GetMapping("/{id}")
    public CatDto one(@PathVariable Long id) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catService.findById(id, principal.getOwner(), principal.getRole());
    }

    @GetMapping
    public List<CatDto> all() {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catService.getAll(principal.getOwner(), principal.getRole());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        catService.delete(id);
    }

    @GetMapping("/{id}/friends")
    public List<CatDto> getCatsFriends(@PathVariable Long id) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catService.getCatFriends(id, principal.getOwner(), principal.getRole());
    }

    @PutMapping("/{id}/add-friend")
    public void addCatsFriend(@PathVariable(value = "id") Long id, @RequestParam(value = "id") Long friendId) {
        catService.addCatFriend(id, friendId);
    }

    @GetMapping("/filter")
    public List<CatDto> filter(
            @RequestParam(value = "color", required = false) Color color,
            @RequestParam(value = "breed", required = false) String breed) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catService.filter(color, breed, principal.getOwner(), principal.getRole());
    }

}
