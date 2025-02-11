package com.fc.task;

import com.fc.NotificationService;
import com.fc.event.follow.FollowEvent;
import com.fc.event.follow.FollowEventType;
import com.fc.notification.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FollowRemoveTask {

    private final NotificationService notificationService;

    public FollowRemoveTask(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processEvent(FollowEvent event) {
        notificationService.getNotificationByTypeAndUserIdAndFollowerId(NotificationType.FOLLOW, event.getTargetUserId(), event.getUserId())
                .ifPresent(notification -> notificationService.deleteById(notification.getId()));
    }
}
