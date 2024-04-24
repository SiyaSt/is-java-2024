package itmo.anastasiya.owner;

import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.OwnerDto;

import java.util.Date;
import java.util.List;

/**
 * contains main owner service methods
 */
public interface OwnerService {
    /**
     * save new owner
     *
     * @param date owner`s birthday
     * @param name owner`s name
     */
    OwnerDto save(Date date, String name);

    /**
     * delete owner
     * @param id owner`s id
     */
    void delete(long id);

    /**
     * find owner by id
     * @param id owner`s id
     * @return owner
     */
    OwnerDto findByIdDto(long id);

    /**
     * get all owner cats
     * @param id owner`s id
     * @return list of cats
     */
    List<CatDto> getAllOwnerCats(long id);

    /**
     * get all owners
     * @return list of owners
     */
    List<OwnerDto> getAll();
}
