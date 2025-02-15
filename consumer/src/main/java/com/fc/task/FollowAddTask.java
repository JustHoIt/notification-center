package com.fc.task;

import com.fc.domain.FollowNotification;
import com.fc.utils.NotificationIdGenerator;
import com.fc.service.NotificationService;
import com.fc.event.FollowEvent;
import com.fc.domain.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class FollowAddTask {

    private final NotificationService notificationService;

    public FollowAddTask(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processEvent(FollowEvent event) {
        notificationService.insert(createFollowNotification(event));
    }

    private static FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();

        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }


}
