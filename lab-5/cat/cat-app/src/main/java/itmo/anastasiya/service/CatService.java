package itmo.anastasiya.service;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.entity.Color;

import java.util.List;

public interface CatService {

    CatDto findById(long catId, long ownerId);

    CatDto findByIdAdmin(long catId);

    List<CatDto> getCatFriends(long catId, long ownerId);

    List<CatDto> getCatFriendsAdmin(long catId);

    List<CatDto> getAll(long ownerId);

    List<CatDto> getAllAdmin();

    List<CatDto> filter(String breed, Color color, long ownerId);

    List<CatDto> filterAdmin(String breed, Color color);
}
