package com.fc.task;

import com.fc.client.PostClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Post;
import com.fc.event.LikeEvent;
import com.fc.domain.Notification;
import com.fc.service.NotificationService;
import com.fc.utils.NotificationIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.fc.domain.NotificationType.LIKE;

@Slf4j
@Component
public class LikeAddTask {

    private final PostClient postClient;

    private final NotificationService notificationService;

    public LikeAddTask(PostClient postClient, NotificationService notificationService) {
        this.postClient = postClient;
        this.notificationService = notificationService;
    }

    public void processEvent(LikeEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (post == null) {
            log.error("Post is null with postId : {}", event.getPostId());
            return;
        }

        if (post.getUserId().equals(event.getUserId())) {
            return;
        }

        notificationService.upsert(createOrUpdateNotification(post, event));
    }

    private Notification createOrUpdateNotification(Post post, LikeEvent event) {
        Optional<Notification> optionalNotification = notificationService.getNotificationByTypeAndPostId(LIKE, post.getId());

        Instant now = Instant.now();
        Instant retention = Instant.now().plus(90, ChronoUnit.DAYS);

        if (optionalNotification.isPresent()) {
            return updateNotification((LikeNotification) optionalNotification.get(), event, now, retention);
        } else {
            return createNotification(post, event, now, retention);
        }
    }

    private Notification updateNotification(LikeNotification notification, LikeEvent event, Instant now, Instant retention) {
        notification.addLiker(event.getUserId(), event.getCreatedAt(), now, retention);
        return notification;
    }

    private Notification createNotification(Post post, LikeEvent event, Instant now, Instant retention) {
        return new LikeNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                LIKE,
                event.getCreatedAt(),
                now,
                now,
                retention,
                post.getId(),
                List.of(event.getUserId())
        );
    }
}
