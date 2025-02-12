package com.fc.task;

import com.fc.service.NotificationService;
import com.fc.event.FollowEvent;
import com.fc.domain.NotificationType;
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
