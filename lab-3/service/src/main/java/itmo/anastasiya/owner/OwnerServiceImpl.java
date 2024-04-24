package itmo.anastasiya.owner;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.exceptions.CatServiceException;
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
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatService catService;

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
        ownerRepository.delete(owner);
    }

    @Override
    public OwnerDto findById(long id) {
        return MappingUtils.mapToOwnerDto(find(id));
    }

    private Owner find(long id) {
        return ownerRepository
                .findById(id)
                .orElseThrow(() -> new CatServiceException("Owner not found"));
    }

    @Override
    public List<CatDto> getAllOwnerCats(long id) {
        var owner = find(id);
        return owner.getCats().stream().map(MappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    @Override
    public List<OwnerDto> getAll() {
        return ownerRepository.findAll().stream().map(MappingUtils::mapToOwnerDto).collect(Collectors.toList());
    }
}
