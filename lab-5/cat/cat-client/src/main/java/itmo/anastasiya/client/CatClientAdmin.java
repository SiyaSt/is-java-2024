package itmo.anastasiya.client;

import itmo.anastasiya.CatDto;

import java.util.List;

public interface CatClientAdmin {
    List<CatDto> getAllAdmin();

    CatDto getOneAdmin(Long id);

    List<CatDto> getCatsFriendsAdmin(Long id);

    List<CatDto> filterBreedAndColorAdmin(String breed, String color);
}
