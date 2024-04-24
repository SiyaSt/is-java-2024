package itmo.anastasiya.dto;

import itmo.anastasiya.entity.Cat;
import itmo.anastasiya.entity.Owner;

public class MappingUtils {

    public static CatDto mapToCatDto(Cat cat) {
        return CatDto
                .builder()
                .id(cat.getId())
                .name(cat.getName())
                .color(cat.getColor())
                .birthdayDate(cat.getBirthdayDate())
                .breed(cat.getBreed())
                .ownerId(cat.getOwner().getId())
                .build();
    }


    public static OwnerDto mapToOwnerDto(Owner owner) {
        return OwnerDto
                .builder()
                .id(owner.getId())
                .birthdayDate(owner.getBirthdayDate())
                .name(owner.getName())
                .build();
    }

    public static Owner mapToOwnerEntity(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setId(ownerDto.getId());
        owner.setName(ownerDto.getName());
        owner.setBirthdayDate(ownerDto.getBirthdayDate());
        return owner;
    }
}
