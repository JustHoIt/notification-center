package com.fc.Service.Convertor;

import com.fc.Service.dto.ConvertedLikeNotification;
import com.fc.client.PostClient;
import com.fc.client.UserClient;
import com.fc.domain.LikeNotification;
import com.fc.domain.Post;
import com.fc.domain.User;
import org.springframework.stereotype.Service;

@Service
public class LikeUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public LikeUserNotificationConvertor(UserClient userClient, PostClient postClient) {
        this.userClient = userClient;
        this.postClient = postClient;
    }


    public ConvertedLikeNotification convert(LikeNotification notification) {
        User user = userClient.getUser(notification.getLikerIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedLikeNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUpdatedAt(),
                user.getUserName(),
                user.getProfileImageUrl(),
                notification.getLikerIds().size(),
                post.getImageUrl()
        );
    }
}
