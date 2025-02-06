package com.fc.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

class NotificationRepositoryMemoryImplTest {
    private final NotificationRepositoryMemoryImpl sut = new NotificationRepositoryMemoryImpl();
    private final Instant now = Instant.now();
    private final Instant deletedAt = Instant.now().plus(90, DAYS);


    @DisplayName("알림 객체를 저장")
    @Test
    void test_save() {
        sut.save(new Notification("1", 2L, NotificationType.LIKE, now, deletedAt));

        Optional<Notification> notification = sut.findById("1");

        assertTrue(notification.isPresent());
    }

    @DisplayName("저장후 조회")
    @Test
    void test_find_by_id() {
        sut.save(new Notification("2", 2L, NotificationType.COMMENT, now, deletedAt));

        Optional<Notification> optionalNotification = sut.findById("2");

        Notification notification = optionalNotification.orElseThrow();
        assertEquals(notification.id, "2");
        assertEquals(notification.userId, 2L);
        assertEquals(notification.notificationType, NotificationType.COMMENT);
        assertEquals(notification.createdAt, now);
        assertEquals(notification.deletedAt, deletedAt);
    }



    @DisplayName("저장 후 삭제")
    @Test
    void test_delete_by_id() {
        sut.save(new Notification("3", 3L, NotificationType.COMMENT, now, deletedAt));
        sut.deleteById("3");

        Optional<Notification> optionalNotification = sut.findById("3");
        assertFalse(optionalNotification.isPresent());
    }
}