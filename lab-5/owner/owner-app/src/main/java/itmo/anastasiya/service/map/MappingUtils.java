package itmo.anastasiya.service.map;

import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.entity.Owner;

public class MappingUtils {
    public static OwnerDto mapToOwnerDto(Owner owner) {
        return OwnerDto
                .builder()
                .id(owner.getId())
                .birthdayDate(owner.getBirthdayDate())
                .name(owner.getName())
                .build();
    }
}
