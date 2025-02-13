package com.fc.Service;

import com.fc.service.NotificationService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CheckNewNotificationService {

    private final NotificationService notificationService;

    private final NotificationReadService notificationReadService;

    public CheckNewNotificationService(NotificationService notificationService, NotificationReadService notificationReadService) {
        this.notificationService = notificationService;
        this.notificationReadService = notificationReadService;
    }


    public boolean checkNewNotification(Long userId) {

        Instant latestUpdatedAt = notificationService.getLatestUpdatedAt(userId);

        if (latestUpdatedAt == null) {
            return false;
        }

        Instant lastReadAt = notificationReadService.getLastReadAt(userId);

        if(lastReadAt == null) {
            return true;
        }


        // latestUpdatedAt 이 lastReadAt 보다 이후라면 true 반환
        return latestUpdatedAt.isAfter(lastReadAt);
    }

}
