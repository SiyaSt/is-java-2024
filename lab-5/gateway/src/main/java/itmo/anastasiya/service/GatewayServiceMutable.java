package itmo.anastasiya.service;

import itmo.anastasiya.CatCreateDto;
import itmo.anastasiya.FriendsDto;
import itmo.anastasiya.OwnerCreateDto;

public interface GatewayServiceMutable {
    void saveOwner(OwnerCreateDto owner);

    void deleteOwner(Long id);

    void saveCat(CatCreateDto cat);

    void deleteCat(Long id);

    void addFriend(FriendsDto friendsDto);
}
