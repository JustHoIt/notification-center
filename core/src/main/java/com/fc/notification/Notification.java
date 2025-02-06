package com.fc.notification;


import java.time.Instant;

public class Notification {
    public String id;
    public Long userId;
    public NotificationType notificationType;
    public Instant createdAt;
    public Instant deletedAt;


    public Notification(String id, Long userId, NotificationType notificationType, Instant createdAt, Instant deletedAt) {
        this.id = id;
        this.userId = userId;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
}
