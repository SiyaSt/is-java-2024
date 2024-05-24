package itmo.anastasiya;

import lombok.Builder;

import java.util.List;

@Builder
public record OwnerListDto(List<OwnerDto> owners) {
}
