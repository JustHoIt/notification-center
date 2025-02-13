package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import com.fc.domain.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void insert(Notification notification) {
        Notification result = notificationRepository.insert(notification);
        log.info("inserted: {}", result);
    }

    public void upsert(Notification notification) {
        Notification result = notificationRepository.save(notification);
        log.info("upserted: {}", result);
    }

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId) {
        log.info("get type and commentId : {} | {} ", type, commentId);
        return notificationRepository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType type, Long postId) {
        log.info("get type and postId : {} | {} ", type, postId);
        return notificationRepository.findByTypeAndPostId(type, postId);
    }


    public void deleteById(String id) {
        log.info("deleted: {}", id);
        notificationRepository.deleteById(id);
    }


    public Optional<Notification> getNotificationByTypeAndUserIdAndFollowerId(NotificationType type, Long userId, Long followerId) {
        log.info("get type and targetUserId and userId : {} | {} | {} ", type, userId, followerId);
        return notificationRepository.findByTypeAndUserIdAndFollowerId(type, userId, followerId);
    }

    public Instant getLatestUpdatedAt(Long userId) {
        Optional<Notification> notification = notificationRepository.findFirstByUserIdOrderByUpdatedAtDesc(userId);

        if(notification.isEmpty()) {
            return null;
        }

        return notification.get().getUpdatedAt();
    }
}

