package com.fc.client;

import com.fc.domain.Post;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostClient {

    private final Map<Long, Post> posts = new HashMap<>();

    public PostClient() {
        posts.put(1L, new Post(1L, 111L, "imageUrl1", "content1"));
        posts.put(2L, new Post(2L, 112L, "imageUrl2", "content2"));
        posts.put(3L, new Post(3L, 113L, "imageUrl3", "content3"));
    }

    public Post getPost(Long id) {
        return posts.get(id);
    }
}
