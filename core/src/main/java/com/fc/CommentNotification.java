package com.fc;

import com.fc.notification.Notification;
import com.fc.notification.NotificationType;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;

@Getter
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification {
    private final Long postId;
    private final Long writerId;
    private final String comment;
    private final Long commentId;
    
    public CommentNotification(
            String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant updatedAt, Instant deletedAt, Long postId, Long writerId, String comment, Long commentId
    ) {
        super(id, userId, type, occurredAt, createdAt, updatedAt, deletedAt);
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
        this.commentId = commentId;
    }
}
