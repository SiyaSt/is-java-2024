package itmo.anastasiya.client;

import itmo.anastasiya.OwnerDto;

import java.util.List;

public interface OwnerClient {
    OwnerDto getOne(Long id);

    List<OwnerDto> getAll();
}
