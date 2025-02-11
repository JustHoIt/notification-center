package com.fc.event.follow;

import com.fc.event.like.LikeEventType;
import com.fc.task.FollowAddTask;
import com.fc.task.FollowRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class FollowEventConsumer {

    private final FollowAddTask followAddTask;

    private final FollowRemoveTask followRemoveTask;

    public FollowEventConsumer(FollowAddTask followAddTask, FollowRemoveTask followRemoveTask) {
        this.followAddTask = followAddTask;
        this.followRemoveTask = followRemoveTask;
    }

    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            if (event.getType() == LikeEventType.ADD) {
                followAddTask.processEvent(event);
            } else if (event.getType() == LikeEventType.REMOVE) {
                followRemoveTask.processEvent(event);
            }
        };
    }
}
