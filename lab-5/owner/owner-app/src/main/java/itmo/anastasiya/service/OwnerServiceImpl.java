package itmo.anastasiya.service;

import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.repository.OwnerRepository;
import itmo.anastasiya.service.exception.OwnerServiceException;
import itmo.anastasiya.service.map.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerDto findById(Long id) {
        var owner = find(id);
        return MappingUtils.mapToOwnerDto(owner);
    }

    @Override
    public List<OwnerDto> getAll() {
        return ownerRepository.findAll().stream().map(MappingUtils::mapToOwnerDto).toList();
    }

    private Owner find(Long id) {
        return ownerRepository
                .findById(id)
                .orElseThrow(() -> OwnerServiceException.noSuchOwner(id));
    }
}
