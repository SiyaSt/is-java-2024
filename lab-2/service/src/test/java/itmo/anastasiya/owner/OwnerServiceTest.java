package itmo.anastasiya.owner;

import itmo.anastasiya.cat.CatDaoStub;
import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.cat.CatServiceImpl;
import itmo.anastasiya.dao.CatDao;
import itmo.anastasiya.dao.OwnerDao;
import itmo.anastasiya.exceptions.CatServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

class OwnerServiceTest {

    OwnerService ownerService;
    OwnerDao ownerDao;
    CatService catService;
    CatDao catDao;

    @BeforeEach
    void init() {
        catDao = new CatDaoStub();
        catService = new CatServiceImpl(catDao);
        ownerDao = new OwnerStub();
        ownerService = new OwnerServiceImpl(ownerDao, catService);
    }

    @Test
    void saveDelete() {
        Assertions.assertThrows(CatServiceException.class, () -> ownerService.findByIdDto(1));
        ownerService.save(Date.from(Instant.now()), "p");
        Assertions.assertDoesNotThrow(() -> ownerService.findByIdDto(1));
        Assertions.assertEquals(ownerService.getAllOwnerCats(1).size(), 0);
        ownerService.delete(1);
        Assertions.assertThrows(CatServiceException.class, () -> ownerService.findByIdDto(1));
    }

}