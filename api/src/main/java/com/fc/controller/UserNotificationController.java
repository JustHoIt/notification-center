package com.fc.controller;

import com.fc.Service.GetUserNotificationsService;
import com.fc.response.UserNotificationListResponse;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/v1/user-notifications")
public class UserNotificationController implements UserNotificationControllerSpec {

    private final GetUserNotificationsService getUserNotificationsService;

    public UserNotificationController(GetUserNotificationsService getUserNotificationsService) {
        this.getUserNotificationsService = getUserNotificationsService;
    }


    @Override
    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ) {

        return UserNotificationListResponse.of(
                getUserNotificationsService.getUserNotificationsByPivot(userId, pivot)
        );

    }

}
