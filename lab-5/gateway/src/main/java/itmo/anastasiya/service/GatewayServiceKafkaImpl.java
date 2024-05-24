package itmo.anastasiya.service;

import itmo.anastasiya.CatCreateDto;
import itmo.anastasiya.FriendsDto;
import itmo.anastasiya.OwnerCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GatewayServiceKafkaImpl implements GatewayServiceMutable {
    private final KafkaTemplate<String, CatCreateDto> saveCat;
    private final KafkaTemplate<String, Long> deleteCat;
    private final KafkaTemplate<String, FriendsDto> addCatFriends;
    private final KafkaTemplate<String, OwnerCreateDto> saveOwner;
    private final KafkaTemplate<String, Long> deleteOwner;

    @Autowired
    public GatewayServiceKafkaImpl(
            KafkaTemplate<String, CatCreateDto> saveCat,
            KafkaTemplate<String, Long> deleteCat,
            KafkaTemplate<String, FriendsDto> addCatFriends,
            KafkaTemplate<String, OwnerCreateDto> saveOwner,
            KafkaTemplate<String, Long> deleteOwner) {
        this.saveCat = saveCat;
        this.deleteCat = deleteCat;
        this.addCatFriends = addCatFriends;
        this.saveOwner = saveOwner;
        this.deleteOwner = deleteOwner;
    }

    @Override
    public void saveOwner(OwnerCreateDto owner) {
        saveOwner.send("save_owner", owner);
    }

    @Override
    public void deleteOwner(Long id) {
        deleteOwner.send("delete_owner", id);
    }

    @Override
    public void saveCat(CatCreateDto cat) {
        saveCat.send("save_cat", cat);
    }

    @Override
    public void deleteCat(Long id) {
        deleteCat.send("delete_cat", id);
    }

    @Override
    public void addFriend(FriendsDto friendsDto) {
        addCatFriends.send("add_cat_friend", friendsDto);
    }
}
