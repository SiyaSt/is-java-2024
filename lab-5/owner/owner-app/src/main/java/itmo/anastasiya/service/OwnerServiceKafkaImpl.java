package itmo.anastasiya.service;

import itmo.anastasiya.OwnerCreateDto;
import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.client.CatClient;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.repository.OwnerRepository;
import itmo.anastasiya.service.exception.OwnerServiceException;
import itmo.anastasiya.service.map.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OwnerServiceKafkaImpl implements OwnerServiceMutable {
    private final OwnerRepository ownerRepository;
    private final CatClient catClient;
    private final KafkaTemplate<String, Long> deleteCat;

    @Autowired
    public OwnerServiceKafkaImpl(
            OwnerRepository ownerRepository,
            CatClient catClient,
            KafkaTemplate<String, Long> getById) {
        this.ownerRepository = ownerRepository;
        this.catClient = catClient;
        this.deleteCat = getById;
    }


    @Override
    @KafkaListener(topics = "save_owner", groupId = "groupIdOwner", containerFactory = "createOwnerFactory")
    public OwnerDto save(OwnerCreateDto ownerCreateDto) {
        var owner = new Owner(ownerCreateDto.name(), ownerCreateDto.birthdayDate());
        ownerRepository.save(owner);
        return MappingUtils.mapToOwnerDto(owner);
    }

    @Override
    @KafkaListener(topics = "delete_owner", groupId = "groupIdOwner", containerFactory = "byIdOwnerFactory")
    public void delete(Long id) {
        var owner = find(id);
        var cats = catClient.getAll(id);
        for (var cat : cats) {
            deleteCat.send("delete_cat", cat.id());
        }
        ownerRepository.delete(owner);

    }

    private Owner find(Long id) {
        return ownerRepository
                .findById(id)
                .orElseThrow(() -> OwnerServiceException.noSuchOwner(id));
    }
}
