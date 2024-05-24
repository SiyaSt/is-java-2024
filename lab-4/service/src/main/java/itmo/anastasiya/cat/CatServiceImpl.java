package itmo.anastasiya.cat;

import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.entity.Role;
import itmo.anastasiya.exceptions.cat.NoneCatException;
import itmo.anastasiya.exceptions.owner.NoneOwnerException;
import itmo.anastasiya.exceptions.user.ForbiddenException;
import itmo.anastasiya.repository.CatRepository;
import itmo.anastasiya.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Override
    @Transactional
    public CatDto save(String name, Date birthdayDate, String breed, Color color, Long ownerId) {
        var owner = ownerRepository
                .findById(ownerId)
                .orElseThrow(() -> NoneOwnerException.noSuchOwner(ownerId));
        var cat = new Cat(name, birthdayDate, breed, color, owner);
        catRepository.save(cat);
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    @Transactional
    public void delete(long id) {
        find(id);
        deleteFriends(id);
        catRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteFriends(long id) {
        var cat = find(id);
        var friends = cat.getFriends();
        for (var friend : friends) {
            var list = friend.getFriends();
            list.remove(cat);
            catRepository.save(friend);
        }
        cat.getFriends().clear();
        catRepository.save(cat);
    }

    @Override
    public OwnerDto getCatOwner(long id) {
        var cat = find(id);
        return MappingUtils.mapToOwnerDto(ownerRepository
                .findById(cat.getOwner().getId())
                .orElseThrow(() -> NoneOwnerException.noSuchOwner(id)));
    }

    @Override
    @Transactional
    public void addCatFriend(long firstCatId, long secondCatId) {
        var firstCat = find(firstCatId);
        var secondCat = find(secondCatId);
        firstCat.addFriend(secondCat);
        secondCat.addFriend(firstCat);
        catRepository.save(firstCat);
        catRepository.save(secondCat);
    }

    @Override
    public CatDto findById(long id, long ownerId, Role role) {
        Cat cat = find(id);
        checkOwner(cat, ownerId, role);
        return MappingUtils.mapToCatDto(cat);
    }

    private Cat find(long id) {
        return catRepository
                .findById(id)
                .orElseThrow(() -> NoneCatException.noSuchCat(id));
    }

    @Override
    public List<CatDto> getCatFriends(long id, long ownerId, Role role) {
        var cat = find(id);
        checkOwner(cat, ownerId, role);
        return checkRole(cat.getFriends(), role, ownerId);
    }

    @Override
    public List<CatDto> getAll(long ownerId, Role role) {
        return checkRole(catRepository.findAll(), role, ownerId);
    }

    @Override
    public List<CatDto> filter(Color color, String breed, long ownerId, Role role) {

        if (color == null) {
            return checkRole(catRepository.findByBreed(breed), role, ownerId);
        }
        if (breed == null) {
            return checkRole(catRepository.findByColor(color), role, ownerId);
        }
        return checkRole(catRepository.findByColorAndBreed(color, breed), role, ownerId);

    }

    private List<CatDto> checkRole(List<Cat> cats, Role role, long ownerId) {
        if (role == Role.ADMIN) {
            return cats.stream()
                    .map(MappingUtils::mapToCatDto)
                    .toList();
        }

        return cats.stream()
                .filter(x -> Objects.equals(x.getOwner().getId(), ownerId))
                .map(MappingUtils::mapToCatDto)
                .toList();
    }

    private void checkOwner(Cat cat, long id, Role role) {
        if (!Objects.equals(id, cat.getOwner().getId()) && role != Role.ADMIN) {
            throw ForbiddenException.accessDenied();
        }
    }
}
