package itmo.anastasiya.service.map;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.entity.Cat;

public class MappingUtils {

    public static CatDto mapToCatDto(Cat cat) {
        return CatDto
                .builder()
                .id(cat.getId())
                .name(cat.getName())
                .color(cat.getColor().name())
                .birthdayDate(cat.getBirthdayDate())
                .breed(cat.getBreed())
                .ownerId(cat.getOwner())
                .build();
    }
}
