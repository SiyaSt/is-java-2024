package itmo.anastasiya.client;

import itmo.anastasiya.CatDto;

import java.util.List;

public interface CatClient {
    List<CatDto> getAll(Long id);

    CatDto getOne(Long id, Long ownerId);

    List<CatDto> getCatsFriends(Long id, Long ownerId);

    List<CatDto> filterBreedAndColor(String color, String breed, Long ownerId);
}
