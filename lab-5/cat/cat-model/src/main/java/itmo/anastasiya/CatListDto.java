package itmo.anastasiya;

import lombok.Builder;

import java.util.List;


@Builder
public record CatListDto(List<CatDto> cats) {
}
