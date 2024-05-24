package itmo.anastasiya.controller;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.CatListDto;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;


    @GetMapping("/{id}")
    public CatDto one(@PathVariable Long id, @RequestParam(value = "owner") Long owner) {
        return catService.findById(id, owner);
    }

    @GetMapping()
    public CatListDto all(@RequestParam(value = "owner") Long owner) {
        return CatListDto.builder().cats(catService.getAll(owner)).build();
    }


    @GetMapping("/{id}/friends")
    public CatListDto getCatsFriends(@PathVariable Long id, @RequestParam(value = "owner") Long owner) {
        return CatListDto.builder().cats(catService.getCatFriends(id, owner)).build();
    }

    @GetMapping("/filter")
    public CatListDto filter(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "breed", required = false) String breed,
            @RequestParam(value = "owner") Long owner) {
        return CatListDto.builder().cats(catService.filter(breed, Color.fromString(color), owner)).build();
    }

}