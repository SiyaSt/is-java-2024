package itmo.anastasiya.cat;

import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.exceptions.CatServiceException;
import itmo.anastasiya.repository.CatRepository;
import itmo.anastasiya.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new CatServiceException("Owner not found"));
        var cat = new Cat(name, birthdayDate, breed, color, owner);
        catRepository.save(cat);
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    @Transactional
    public void delete(long id) {
        var cat = findById(id);
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
        var cat = findById(id);
        return MappingUtils.mapToOwnerDto(ownerRepository
                .findById(cat.getOwnerId())
                .orElseThrow(() -> new CatServiceException("Cat not found")));
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
    public CatDto findById(long id) {
        return MappingUtils.mapToCatDto(find(id));
    }

    private Cat find(long id) {
        return catRepository
                .findById(id)
                .orElseThrow(() -> new CatServiceException("Cat not found"));
    }

    @Override
    public List<CatDto> getCatFriends(long id) {
        var cat = find(id);
        return cat.getFriends().stream().map(MappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    @Override
    public List<CatDto> getAll() {
        return catRepository.findAll().stream().map(MappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    @Override
    public List<CatDto> filter(Color color, String breed) {

        if (color == null) {
            return catRepository.findByBreed(breed).stream()
                    .map(MappingUtils::mapToCatDto)
                    .collect(Collectors.toList());
        }
        if (breed == null) {
            return catRepository.findByColor(color).stream()
                    .map(MappingUtils::mapToCatDto)
                    .collect(Collectors.toList());
        }
        return catRepository
                .findByColorAndBreed(color, breed)
                .stream()
                .map(MappingUtils::mapToCatDto)
                .collect(Collectors.toList());

    }
}
