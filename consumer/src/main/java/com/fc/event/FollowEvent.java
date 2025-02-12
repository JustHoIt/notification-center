package com.fc.event;

import lombok.Data;

import java.time.Instant;

@Data
public class FollowEvent {
    private LikeEventType type;
    private Long userId;
    private Long targetUserId;
    private Instant createdAt;

}
