package com.fc.event.follow;

import com.fc.event.like.LikeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class FollowEventConsumer {

    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> log.info(event.toString());
    }
}
