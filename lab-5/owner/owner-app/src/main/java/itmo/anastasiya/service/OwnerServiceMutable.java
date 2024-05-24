package itmo.anastasiya.service;

import itmo.anastasiya.OwnerCreateDto;
import itmo.anastasiya.OwnerDto;

public interface OwnerServiceMutable {
    OwnerDto save(OwnerCreateDto ownerCreateDto);

    void delete(Long id);
}
