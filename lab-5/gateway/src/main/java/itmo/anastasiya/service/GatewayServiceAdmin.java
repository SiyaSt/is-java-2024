package itmo.anastasiya.service;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.OwnerDto;

import java.util.List;

public interface GatewayServiceAdmin {
    OwnerDto getOwner(Long id);

    List<OwnerDto> allOwners();

    CatDto getCatAdmin(Long id);

    List<CatDto> getAllCatsAdmin();

    List<CatDto> getCatsFriendsAdmin(Long id);

    List<CatDto> filterAdmin(String color, String breed);
}
