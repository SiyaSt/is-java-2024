package itmo.anastasiya.service;

import itmo.anastasiya.CatCreateDto;
import itmo.anastasiya.CatDto;
import itmo.anastasiya.FriendsDto;

public interface CatServiceMutable {
    CatDto save(CatCreateDto catCreateDto);

    void delete(long id);

    void addCatFriend(FriendsDto friendsDto);
}
