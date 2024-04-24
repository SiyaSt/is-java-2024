package itmo.anastasiya.cat;

import itmo.anastasiya.dao.CatDao;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.exceptions.CatServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static itmo.anastasiya.entity.Color.WHITE;

class CatServiceTest {

    CatService catService;
    CatDao catDao;
    Owner owner;
    Owner secondOwner;

    @BeforeEach
    public void init() {
        catDao = new CatDaoStub();
        catService = new CatServiceImpl(catDao);
        owner = new Owner("p", Date.from(Instant.now()));
        secondOwner = new Owner("p", Date.from(Instant.now()));
    }

    @Test
    void saveDelete() {
        Assertions.assertThrows(CatServiceException.class, () -> catService.findByIdDto(1));
        catService.save("p", Date.from(Instant.now()), "eee", WHITE, MappingUtils.mapToOwnerDto(owner));
        Assertions.assertDoesNotThrow(() -> catService.findByIdDto(1));
        catService.delete(1);
        Assertions.assertThrows(CatServiceException.class, () -> catService.findByIdDto(1));
    }

    @Test
    void addDeleteFriendsWithSameOwner() {
        Assertions.assertThrows(CatServiceException.class, () -> catService.findByIdDto(1));
        Assertions.assertThrows(CatServiceException.class, () -> catService.findByIdDto(2));
        catService.save("p", Date.from(Instant.now()), "eee", WHITE, MappingUtils.mapToOwnerDto(owner));
        catService.save("p", Date.from(Instant.now()), "eee", WHITE, MappingUtils.mapToOwnerDto(owner));
        Assertions.assertDoesNotThrow(() -> catService.findByIdDto(1));
        Assertions.assertDoesNotThrow(() -> catService.findByIdDto(2));
        Assertions.assertEquals(catService.getCatFriends(1).size(), 0);
        Assertions.assertEquals(catService.getCatFriends(2).size(), 0);
        catService.addCatFriend(1, 2);
        Assertions.assertEquals(catService.getCatFriends(1).size(), 1);
        Assertions.assertEquals(catService.getCatFriends(2).size(), 1);
        catService.deleteFriends(1);
        Assertions.assertEquals(catService.getCatFriends(1).size(), 0);
        Assertions.assertEquals(catService.getCatFriends(2).size(), 0);
    }
    @Test
    void addDeleteFriendsWithDifferentOwner() {
        Assertions.assertThrows(CatServiceException.class, () -> catService.findByIdDto(1));
        Assertions.assertThrows(CatServiceException.class, () -> catService.findByIdDto(2));
        catService.save("p", Date.from(Instant.now()), "eee", WHITE, MappingUtils.mapToOwnerDto(owner));
        catService.save("p", Date.from(Instant.now()), "eee", WHITE, MappingUtils.mapToOwnerDto(secondOwner));
        Assertions.assertDoesNotThrow(() -> catService.findByIdDto(1));
        Assertions.assertDoesNotThrow(() -> catService.findByIdDto(2));
        Assertions.assertEquals(catService.getCatFriends(1).size(), 0);
        Assertions.assertEquals(catService.getCatFriends(2).size(), 0);
        catService.addCatFriend(1, 2);
        Assertions.assertEquals(catService.getCatFriends(1).size(), 1);
        Assertions.assertEquals(catService.getCatFriends(2).size(), 1);
        catService.deleteFriends(1);
        Assertions.assertEquals(catService.getCatFriends(1).size(), 0);
        Assertions.assertEquals(catService.getCatFriends(2).size(), 0);
    }
}