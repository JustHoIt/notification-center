package com.fc.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Document("notifications")
public abstract class Notification {
    private String id;
    private Long userId;
    private NotificationType notificationType;
    private Instant occurredAt; // 이벤트 발생 시간
    private Instant createdAt; // 알림이 생성된 시간
    private Instant updatedAt; // 알림이 업데이트된 시간
    private Instant deletedAt; // 알림이 삭제될 시간
}
