package com.fc.controller;

import com.fc.response.UserNotificationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.Instant;

@Tag(name = "사용자 알림센터 API")
public interface UserNotificationControllerSpec {

    @Operation(summary = "사용자 알림 목록 조회")
    UserNotificationListResponse getNotifications(
            @Parameter(example = "1L") Long userId,
            @Parameter(example = "2025-01-01T00:11:22.123Z") Instant pivot
    );
}
