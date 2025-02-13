package com.fc.Service;

import com.fc.repository.NotificationReadRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class NotificationReadService {

    private final NotificationReadRepository repository;

    public NotificationReadService(NotificationReadRepository repository) {
        this.repository = repository;
    }

    public Instant setLastReadAt(Long userId) {
        return repository.setLastReadAt(userId);
    }

    public Instant getLastReadAt(Long userId) {
        return repository.getLastReadAt(userId);
    }
}
