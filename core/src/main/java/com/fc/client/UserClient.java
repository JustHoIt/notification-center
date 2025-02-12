package com.fc.client;

import com.fc.domain.User;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserClient {

    private final Map<Long, User> users = new HashMap<>();

    public UserClient() {
        Instant now = Instant.now();
        users.put(1L, new User(1L, "userName1", "profileImageUrl1"));
        users.put(2L, new User(2L, "userName2", "profileImageUrl2"));
        users.put(3L, new User(3L, "userName3", "profileImageUrl3"));
        users.put(4L, new User(4L, "userName4", "profileImageUrl4"));
        users.put(5L, new User(5L, "userName5", "profileImageUrl5"));
    }

    public User getUser(long userId) {
        return users.get(userId);
    }

    public Boolean getIsFollowing(Long followerId, Long followedId) {
        return true;
    }

}
