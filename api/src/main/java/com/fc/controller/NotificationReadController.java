package com.fc.controller;

import com.fc.Service.NotificationReadService;
import com.fc.response.SetLastReadAtResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/v1/user-notifications")
public class NotificationReadController implements NotificationReadControllerSpec{

    private final NotificationReadService notificationReadService;

    public NotificationReadController(NotificationReadService notificationReadService) {
        this.notificationReadService = notificationReadService;
    }


    @Override
    @PutMapping("/{userId}")
    public SetLastReadAtResponse setLastReadAt(
            @PathVariable Long userId
    ) {
        Instant lastReadAt = notificationReadService.setLastReadAt(userId);

        return new SetLastReadAtResponse(lastReadAt);
    }
}
