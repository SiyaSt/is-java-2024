package itmo.anastasiya;

import lombok.Builder;


@Builder
public record FriendsDto(long firstFriend, long secondFriend) {
}
