package com.fc;

import com.fc.notification.Notification;
import com.fc.notification.NotificationRepository;
import com.fc.notification.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void insert(Notification notification) {
        Notification result = notificationRepository.save(notification);
        log.info("inserted: {}", result);
    }

    public Optional<Notification> getNotification(NotificationType type, Long commentId) {
        return notificationRepository.findByTypeAndCommentId(type, commentId);
    }


    public void deleteNotification(String id) {
        log.info("deleted: {}", id);
        notificationRepository.deleteById(id);
    }


}

