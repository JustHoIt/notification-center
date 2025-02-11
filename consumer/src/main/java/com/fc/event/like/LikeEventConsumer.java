package com.fc.event.like;

import com.fc.task.LikeAddTask;
import com.fc.task.LikeRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class LikeEventConsumer {

    private final LikeAddTask likeAddTask;

    private final LikeRemoveTask likeRemoveTask;

    public LikeEventConsumer(LikeAddTask likeAddTask, LikeRemoveTask likeRemoveTask) {
        this.likeAddTask = likeAddTask;
        this.likeRemoveTask = likeRemoveTask;
    }

    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> {
            if (event.getType() == LikeEventType.ADD) {
                likeAddTask.processEvent(event);
            }else if(event.getType() == LikeEventType.REMOVE) {
                likeRemoveTask.processEvent(event);
            }
        };
    }
}
