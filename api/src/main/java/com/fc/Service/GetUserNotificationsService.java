package com.fc.Service;

import com.fc.Service.Convertor.CommentUserNotificationConvertor;
import com.fc.Service.Convertor.FollowUserNotificationConvertor;
import com.fc.Service.Convertor.LikeUserNotificationConvertor;
import com.fc.Service.dto.ConvertedNotification;
import com.fc.Service.dto.GetUserNotificationResult;
import com.fc.domain.CommentNotification;
import com.fc.domain.FollowNotification;
import com.fc.domain.LikeNotification;
import com.fc.service.NotificationListService;
import com.fc.service.dto.GetUserNotificationByPivotResult;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class GetUserNotificationsService {
    private final NotificationListService notificationListService;

    private final CommentUserNotificationConvertor commentConvertor;

    private final LikeUserNotificationConvertor likeConvertor;

    private final FollowUserNotificationConvertor followConvertor;


    public GetUserNotificationsService(NotificationListService notificationListService, CommentUserNotificationConvertor commentConvertor, LikeUserNotificationConvertor likeConvertor, FollowUserNotificationConvertor followConvertor) {
        this.notificationListService = notificationListService;
        this.commentConvertor = commentConvertor;
        this.likeConvertor = likeConvertor;
        this.followConvertor = followConvertor;
    }

    public GetUserNotificationResult getUserNotificationsByPivot(long userId, Instant pivot) {
        // 알림 목록 조회
        GetUserNotificationByPivotResult result = notificationListService.getUserNotificationsByPivot(userId, pivot);

        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConvertor.convert((CommentNotification) notification);
                    case LIKE -> likeConvertor.convert((LikeNotification) notification);
                    case FOLLOW -> followConvertor.convert((FollowNotification) notification);
                })
                .toList();

        // 알림 목록을 순회하면서 디비 알림 -> 사용자 알림으로 변환
        return new GetUserNotificationResult(
                convertedNotifications,
                result.isHasNext()
        );

    }
}