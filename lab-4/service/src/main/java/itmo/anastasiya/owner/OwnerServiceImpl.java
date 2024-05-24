package itmo.anastasiya.owner;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.exceptions.owner.NoneOwnerException;
import itmo.anastasiya.repository.OwnerRepository;
import itmo.anastasiya.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatService catService;
    private final UserService userService;

    @Override
    @Transactional
    public OwnerDto save(Date date, String name) {
        var owner = new Owner(name, date);
        ownerRepository.save(owner);
        return MappingUtils.mapToOwnerDto(owner);
    }

    @Override
    @Transactional
    public void delete(long id) {
        var owner = find(id);
        owner.getCats().forEach(cat -> catService.deleteFriends(cat.getId()));
        userService.delete(id);
        ownerRepository.delete(owner);
    }

    @Override
    public OwnerDto findById(long id) {
        return MappingUtils.mapToOwnerDto(find(id));
    }

    private Owner find(long id) {
        return ownerRepository
                .findById(id)
                .orElseThrow(() -> NoneOwnerException.noSuchOwner(id));
    }

    @Override
    public List<CatDto> getAllOwnerCats(long id) {
        var owner = find(id);
        return owner.getCats().stream().map(MappingUtils::mapToCatDto).toList();
    }

    @Override
    public List<OwnerDto> getAll() {
        return ownerRepository.findAll().stream().map(MappingUtils::mapToOwnerDto).toList();
    }
}
