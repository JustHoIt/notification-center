package com.fc.task;


import com.fc.LikeNotification;
import com.fc.NotificationService;
import com.fc.PostClient;
import com.fc.event.like.LikeEvent;
import com.fc.notification.Notification;
import com.fc.notification.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static com.fc.notification.NotificationType.LIKE;

@Component
@Slf4j
public class LikeRemoveTask {

    @Autowired
    PostClient postClient;

    @Autowired
    NotificationService notificationService;

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
