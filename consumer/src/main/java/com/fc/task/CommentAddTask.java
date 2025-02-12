package com.fc.task;

import com.fc.client.CommentClient;
import com.fc.client.PostClient;
import com.fc.domain.Comment;
import com.fc.domain.CommentNotification;
import com.fc.domain.Post;
import com.fc.event.CommentEvent;
import com.fc.domain.Notification;
import com.fc.domain.NotificationType;
import com.fc.service.NotificationService;
import com.fc.utils.NotificationIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Component
@Slf4j
public class CommentAddTask {

    private final PostClient postClient;

    private final CommentClient commentClient;

    private final NotificationService notificationService;

    public CommentAddTask(PostClient postClient, CommentClient commentClient, NotificationService notificationService) {
        this.postClient = postClient;
        this.commentClient = commentClient;
        this.notificationService = notificationService;
    }

    public void processEvent(CommentEvent event) {
        // 내가 작성한 댓글인 경우 무시
        Post post = postClient.getPost(event.getPostId());
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }
        Comment comment = commentClient.getComment(event.getCommentId());

        // 알림 생성
        Notification notification = createNotification(post, comment);

        // 저장
        notificationService.insert(notification);
    }

    private Notification createNotification(Post post, Comment comment) {
        Instant now = Instant.now();
        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getId()
        );
    }
}
