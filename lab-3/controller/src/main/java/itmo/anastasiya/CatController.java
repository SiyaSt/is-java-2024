package itmo.anastasiya;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.entity.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;

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
        return catService.findById(id);
    }

    @GetMapping
    public List<CatDto> all() {
        return catService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        catService.delete(id);
    }

    @GetMapping("/{id}/friends")
    public List<CatDto> getCatsFriends(@PathVariable Long id) {
        return catService.getCatFriends(id);
    }

    @PutMapping("/{id}/add-friend")
    public void addCatsFriend(@PathVariable(value = "id") Long id, @RequestParam(value = "id") Long friendId) {
        catService.addCatFriend(id, friendId);
    }

    @GetMapping("/filter")
    public List<CatDto> filter(
            @RequestParam(value = "color", required = false) Color color,
            @RequestParam(value = "breed", required = false) String breed) {
        return catService.filter(color, breed);
    }

}
