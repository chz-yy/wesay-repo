package com.chat.wesay.pojo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendShipResponse {
    private Long friendshipId;
    private Long friendId;
    private String friendName;
    private String friendAvatar;

    public FriendShipResponse(Long friendshipId, Long friendId, String friendName) {
        this.friendshipId = friendshipId;
        this.friendId = friendId;
        this.friendName = friendName;
    }
}
