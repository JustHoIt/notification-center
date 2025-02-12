package com.fc.service;

import com.fc.domain.Notification;
import com.fc.repository.NotificationRepository;
import com.fc.service.dto.GetUserNotificationByPivotResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationListService {

    private final NotificationRepository notificationRepository;

    private static final int PAGE_SIZE = 20;

    public NotificationListService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // 목록 조회: Pivot 방식 (기준점 : occurredAt, size) vs Paging 방식(page size, page number)
    public GetUserNotificationByPivotResult getUserNotificationsByPivot(Long userId, Instant occurredAt) {
        Slice<Notification> result;

        if (occurredAt == null) {
            result = notificationRepository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        } else {
            result = notificationRepository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }

        return new GetUserNotificationByPivotResult(
                result.toList(),
                result.hasNext()
        );
    }
}
