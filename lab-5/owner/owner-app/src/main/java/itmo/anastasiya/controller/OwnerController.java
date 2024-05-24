package itmo.anastasiya.controller;

import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.OwnerListDto;
import itmo.anastasiya.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;


    @GetMapping("/{id}")
    public OwnerDto one(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping
    public OwnerListDto all() {
        return OwnerListDto.builder().owners(ownerService.getAll()).build();
    }

}