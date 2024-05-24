package itmo.anastasiya.cat;

import itmo.anastasiya.dto.CatDto;
import itmo.anastasiya.dto.OwnerDto;
import itmo.anastasiya.entity.Color;
import itmo.anastasiya.entity.Role;

import java.util.Date;
import java.util.List;

/**
 * contains cat service methods
 *
 * @author Anastasiya
 */
public interface CatService {
    /**
     * save new cat
     * @param name cat`s name
     * @param birthdayDate cat`s birthday
     * @param breed cat`s breed
     * @param color cat`s color
     * @param ownerId cat`s owner
     */
    CatDto save(String name, Date birthdayDate, String breed, Color color, Long ownerId);

    /**
     * delete cat with id
     * @param id cat`s id
     */
    void delete(long id);

    /**
     * delete relationship between cat and his friends
     * @param id cat`s id
     */
    void deleteFriends(long id);

    /**
     * get cat`s owner
     * @param id cat`s id
     * @return owner
     */
    OwnerDto getCatOwner(long id);

    /**
     * add new friend to cat
     * @param firstCatId first cat id
     * @param secondCatId second cat id
     */
    void addCatFriend(long firstCatId, long secondCatId);

    /**
     * find cat by id
     *
     * @param id     cat`s id
     * @param ownerId
     * @return cat
     */
    CatDto findById(long id, long ownerId, Role role);


    /**
     * gat all cat friends
     * @param id cat`s id
     * @return list of cats
     */
    List<CatDto> getCatFriends(long id, long ownerId, Role role);

    /**
     * get all cats
     * @return list of cats
     */
    List<CatDto> getAll(long ownerId, Role role);

    List<CatDto> filter(Color color, String breed, long ownerId, Role role);
}
