package itmo.anastasiya.controller;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final GatewayService gatewayService;

    @Autowired
    public UserController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping("/cat/{id}")
    public CatDto getOneCat(@PathVariable Long id) {
        return gatewayService.getCat(id);
    }

    @GetMapping("/cats")
    public List<CatDto> getAllCats() {
        return gatewayService.getAllCats();
    }

    @GetMapping("cat/{id}/friends")
    public List<CatDto> getCatsFriends(@PathVariable Long id) {
        return gatewayService.getCatsFriends(id);
    }

    @GetMapping("/filter")
    public List<CatDto> filter(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "breed", required = false) String breed) {
        return gatewayService.filter(color, breed);
    }
}
