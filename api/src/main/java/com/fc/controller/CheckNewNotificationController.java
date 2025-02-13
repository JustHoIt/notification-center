package com.fc.controller;

import com.fc.Service.CheckNewNotificationService;
import com.fc.response.CheckNewNotificationResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-notifications")
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec {

    private final CheckNewNotificationService checkNewNotificationService;

    public CheckNewNotificationController(CheckNewNotificationService checkNewNotificationService) {
        this.checkNewNotificationService = checkNewNotificationService;
    }

    @Override
    @GetMapping("/{userId}/new")
    public CheckNewNotificationResponse checkNew(
            @PathVariable(value = "userId") Long userId
    ) {
        boolean hasNew = checkNewNotificationService.checkNewNotification(userId);

        return new CheckNewNotificationResponse(hasNew);
    }
}
