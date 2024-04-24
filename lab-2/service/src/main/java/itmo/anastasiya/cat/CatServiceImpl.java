package itmo.anastasiya.cat;

import itmo.anastasiya.dao.CatDao;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.exceptions.CatServiceException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {
    private final CatDao catDao;

    public CatServiceImpl(CatDao catDao) {
        this.catDao = catDao;
    }

    @Override
    public CatDto save(String name, Date birthdayDate, String breed, Color color, OwnerDto owner) {
        var cat = new Cat(name, birthdayDate, breed, color, MappingUtils.mapToOwnerEntity(owner));
        catDao.save(cat);
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    public void delete(long id) {

        var cat = findById(id);
        deleteFriends(id);
        catDao.deleteByEntity(cat);
    }

    @Override
    public void deleteFriends(long id) {
        var cat = findById(id);
        var friends = cat.getFriends();
        for (var friend : friends) {
            var list = friend.getFriends();
            list.remove(cat);
            catDao.update(friend);
        }
        cat.getFriends().clear();
        catDao.update(cat);
    }

    @Override
    public OwnerDto getCatOwner(long id) {
        var cat = findById(id);
        return MappingUtils.mapToOwnerDto(cat.getOwner());
    }

    @Override
    public void addCatFriend(long firstCatId, long secondCatId) {
        var firstCat = findById(firstCatId);
        var secondCat = findById(secondCatId);
        firstCat.addFriend(secondCat);
        secondCat.addFriend(firstCat);
        catDao.update(firstCat);
        catDao.update(secondCat);
    }

    private Cat findById(long id) {
        var cat = catDao.getById(id);
        if (cat == null) {
            throw new CatServiceException("No such cat");
        }
        return cat;
    }
    @Override
    public CatDto findByIdDto(long id) {
        var cat = catDao.getById(id);
        if (cat == null) {
            throw new CatServiceException("No such cat");
        }
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    public List<CatDto> getCatFriends(long id) {
        var cat = findById(id);
        return cat.getFriends().stream().map(MappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    @Override
    public List<CatDto> getAll() {
        return catDao.getAll().stream().map(MappingUtils::mapToCatDto).collect(Collectors.toList());
    }
}
