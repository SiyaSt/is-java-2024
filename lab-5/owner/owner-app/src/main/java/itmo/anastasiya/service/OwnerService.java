package itmo.anastasiya.service;

import itmo.anastasiya.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto findById(Long id);

    List<OwnerDto> getAll();
}
