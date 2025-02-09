package com.fc.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@Document("notifications")
public abstract class Notification {
    @Field(targetType = FieldType.STRING)
    private String id;
    private Long userId;
    private NotificationType type;
    private Instant occurredAt; // 이벤트 발생 시간
    private Instant createdAt; // 알림이 생성된 시간
    private Instant updatedAt; // 알림이 업데이트된 시간
    private Instant deletedAt; // 알림이 삭제될 시간
}
