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
@RequestMapping("/api/cats/admin")
@RequiredArgsConstructor
public class CatControllerAdmin {
    private final CatService catService;

    @GetMapping("/{id}")
    public CatDto oneAdmin(@PathVariable Long id) {
        return catService.findByIdAdmin(id);
    }

    @GetMapping()
    public CatListDto allAdmin() {
        return CatListDto.builder().cats(catService.getAllAdmin()).build();
    }


    @GetMapping("/{id}/friends")
    public CatListDto getCatsFriendsAdmin(@PathVariable Long id) {
        return CatListDto.builder().cats(catService.getCatFriendsAdmin(id)).build();
    }

    @GetMapping("/filter")
    public CatListDto filterAdmin(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "breed", required = false) String breed) {
        return CatListDto.builder().cats(catService.filterAdmin(breed, Color.fromString(color))).build();
    }
}
