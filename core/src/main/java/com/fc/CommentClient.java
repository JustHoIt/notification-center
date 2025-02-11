package com.fc;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentClient {

    private final Map<Long, Comment> comments = new HashMap<>();

    public CommentClient() {
        Instant now = Instant.now();
        comments.put(1L, new Comment(1L, 111L, "content1", now));
        comments.put(2L, new Comment(2L, 112L, "content2", now));
        comments.put(3L, new Comment(3L, 113L, "content3", now));
    }

    public Comment getComment(Long id) {
        return comments.get(id);
    }
}
