package itmo.anastasiya;

import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.owner.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    public OwnerDto saveOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.save(ownerDto.getBirthdayDate(), ownerDto.getName());
    }

    @GetMapping("/{id}")
    public OwnerDto one(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping
    public List<OwnerDto> all() {
        return ownerService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.delete(id);
    }

    @GetMapping("/{id}/cats")
    public List<CatDto> getOwnerCats(@PathVariable Long id) {
        return ownerService.getAllOwnerCats(id);
    }

}
