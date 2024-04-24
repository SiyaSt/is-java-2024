package itmo.anastasiya.dto;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Owner;

public class MappingUtils {

    public static CatDto mapToCatDto(Cat cat) {
        return new CatDto(
                cat.getId(),
                cat.getName(),
                cat.getBirthdayDate(),
                cat.getBreed(),
                cat.getColor(),
                cat.getOwner(),
                cat.getFriends());
    }


    public static OwnerDto mapToOwnerDto(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthdayDate(), owner.getCats());
    }

    public static Owner mapToOwnerEntity(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setBirthdayDate(ownerDto.getBirthdayDate());
        return owner;
    }
}
