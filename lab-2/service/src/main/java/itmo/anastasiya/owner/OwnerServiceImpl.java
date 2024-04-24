package itmo.anastasiya.owner;

import itmo.anastasiya.cat.CatService;
import itmo.anastasiya.dao.OwnerDao;
import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.MappingUtils;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Owner;
import itmo.anastasiya.exceptions.CatServiceException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao;
    private final CatService catService;

    public OwnerServiceImpl(OwnerDao ownerDao, CatService catService) {
        this.ownerDao = ownerDao;
        this.catService = catService;
    }

    @Override
    public OwnerDto save(Date date, String name) {
        var owner = new Owner(name, date);
        ownerDao.save(new Owner(name, date));
        return MappingUtils.mapToOwnerDto(owner);
    }

    @Override
    public void delete(long id) {
        var owner = findById(id);
        owner.getCats().forEach(cat -> catService.deleteFriends(cat.getId()));
        ownerDao.deleteByEntity(owner);
    }

    private Owner findById(long id) {
        var owner = ownerDao.getById(id);
        if (owner == null) {
            throw new CatServiceException("No such owner");
        }
        return owner;
    }

    public OwnerDto findByIdDto(long id) {
        var owner = ownerDao.getById(id);
        if (owner == null) {
            throw new CatServiceException("No such owner");
        }
        return MappingUtils.mapToOwnerDto(owner);
    }
    @Override
    public List<CatDto> getAllOwnerCats(long id) {
        var owner = findById(id);
        return owner.getCats().stream().map(MappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    @Override
    public List<OwnerDto> getAll() {
        return ownerDao.getAll().stream().map(MappingUtils::mapToOwnerDto).collect(Collectors.toList());
    }
}
