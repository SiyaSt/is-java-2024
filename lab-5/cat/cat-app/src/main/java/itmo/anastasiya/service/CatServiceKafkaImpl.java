package itmo.anastasiya.service;

import itmo.anastasiya.CatCreateDto;
import itmo.anastasiya.CatDto;
import itmo.anastasiya.FriendsDto;
import itmo.anastasiya.client.OwnerClient;
import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.repository.CatRepository;
import itmo.anastasiya.service.exceptions.CatServiceException;
import itmo.anastasiya.service.map.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CatServiceKafkaImpl implements CatServiceMutable {
    private final CatRepository catRepository;
    private final OwnerClient ownerClient;

    @Autowired
    public CatServiceKafkaImpl(CatRepository catRepository, OwnerClient ownerClient) {
        this.catRepository = catRepository;
        this.ownerClient = ownerClient;
    }

    @Override
    @Transactional
    @KafkaListener(topics = "save_cat", groupId = "groupIdCat", containerFactory = "createCatFactory")
    public CatDto save(CatCreateDto catCreateDto) {
        Long ownerId = ownerClient.getOne(catCreateDto.ownerId()).id();
        Cat cat = new Cat(
                catCreateDto.name(),
                catCreateDto.birthdayDate(),
                catCreateDto.breed(),
                Color.fromString(catCreateDto.color()),
                catCreateDto.ownerId());
        catRepository.save(cat);
        return MappingUtils.mapToCatDto(cat);
    }

    @Override
    @Transactional
    @KafkaListener(topics = "delete_cat", groupId = "groupIdCat", containerFactory = "byIdCatFactory")
    public void delete(long id) {
        var cat = find(id);
        deleteFriends(id);
        catRepository.delete(cat);
    }

    @Override
    @Transactional
    @KafkaListener(topics = "add_cat_friend", groupId = "groupIdCat", containerFactory = "friendFactory")
    public void addCatFriend(FriendsDto friendsDto) {
        var firstCat = find(friendsDto.firstFriend());
        var secondCat = find(friendsDto.secondFriend());
        firstCat.addFriend(secondCat);
        secondCat.addFriend(firstCat);
        catRepository.save(firstCat);
        catRepository.save(secondCat);
    }

    private Cat find(long id) {
        return catRepository
                .findById(id)
                .orElseThrow(() -> CatServiceException.noSuchCat(id));
    }

    private void deleteFriends(long id) {
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
}
