package com.fc.domain;

import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;
import java.util.List;

@Getter
@TypeAlias("LikeNotification")
public class LikeNotification extends Notification {
    private final Long postId;
    private final List<Long> likerIds;

    public LikeNotification(
            String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt,
                            Instant updatedAt, Instant deletedAt, Long postId, List<Long> likerIds
    ) {
        super(id, userId, type, occurredAt, createdAt, updatedAt, deletedAt);
        this.postId = postId;
        this.likerIds = likerIds;
    }

    public void addLiker(Long likerId, Instant occurredAt, Instant now, Instant retention) {
        this.likerIds.add(likerId);
        this.setOccurredAt(occurredAt);
        this.setUpdatedAt(now);
        this.setDeletedAt(retention);
    }

    public void removeLiker(Long likerId, Instant now) {
        this.likerIds.remove(likerId);
        this.setUpdatedAt(now);
    }
}
