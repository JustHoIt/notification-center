package com.fc.task;

import com.fc.NotificationService;
import com.fc.Post;
import com.fc.PostClient;
import com.fc.event.comment.CommentEvent;
import com.fc.notification.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class CommentRemoveTask {

    @Autowired
    PostClient postClient;

    @Autowired
    NotificationService notificationService;

    public void processEvent(CommentEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }

        notificationService.getNotification(NotificationType.COMMENT, event.getCommentId())
                .ifPresentOrElse(
                        notification -> notificationService.deleteNotification(notification.getId()),
                        () -> log.error("Notification Not Found")
                );
    }
}
