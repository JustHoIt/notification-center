package com.fc.task;


import com.fc.domain.LikeNotification;
import com.fc.service.NotificationService;
import com.fc.client.PostClient;
import com.fc.event.LikeEvent;
import com.fc.domain.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static com.fc.domain.NotificationType.LIKE;

@Component
@Slf4j
public class LikeRemoveTask {

    private final NotificationService notificationService;

    public LikeRemoveTask(PostClient postClient, NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processEvent(LikeEvent event) {
        Optional<Notification> optionalNotification = notificationService.getNotificationByTypeAndPostId(LIKE, event.getPostId());

        if (optionalNotification.isEmpty()) {
            log.error("No notification with postId : {} ", event.getPostId());
            return;
        }

        LikeNotification notification = (LikeNotification) optionalNotification.get();
        removeLikerAndUpdateNotification(notification, event);
    }

    private void removeLikerAndUpdateNotification(LikeNotification notification, LikeEvent event) {
        notification.removeLiker(event.getUserId(), Instant.now());

        if (notification.getLikerIds().isEmpty()) {
            notificationService.deleteById(notification.getId());
        } else {
            notificationService.upsert(notification);
        }
    }
}
