package com.fc.Service.Convertor;

import com.fc.Service.dto.ConvertedFollowNotification;
import com.fc.client.UserClient;
import com.fc.domain.FollowNotification;
import com.fc.domain.User;
import org.springframework.stereotype.Service;

@Service
public class FollowUserNotificationConvertor {

    private final UserClient userClient;

    public FollowUserNotificationConvertor(UserClient userClient) {
        this.userClient = userClient;
    }


    public ConvertedFollowNotification convert(FollowNotification notification) {

        User user = userClient.getUser(notification.getFollowerId());
        boolean isFollowing = userClient.getIsFollowing(notification.getUserId(), notification.getFollowerId());

        return new ConvertedFollowNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUpdatedAt(),
                user.getUserName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }
}
