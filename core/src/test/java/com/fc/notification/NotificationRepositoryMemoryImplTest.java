//package com.fc.notification;
//
//import com.fc.domain.CommentNotification;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.Instant;
//import java.util.Optional;
//
//import static java.time.temporal.ChronoUnit.DAYS;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@SpringBootApplication
//class NotificationRepositoryMemoryImplTest {
//
//    @Autowired
//    private NotificationRepository sut;
//
//    private final Instant now = Instant.now();
//    private final Instant ninetyDaysAfter = Instant.now().plus(90, DAYS);
//
//
//    @DisplayName("알림 객체를 저장")
//    @Test
//    void test_save() {
//        sut.save(new CommentNotification("1", 2L, NotificationType.LIKE, now, now, ninetyDaysAfter, null));
//
//        Optional<Notification> notification = sut.findById("1");
//
//        assertTrue(notification.isPresent());
//    }
//
//    @DisplayName("저장후 조회")
//    @Test
//    void test_find_by_id() {
//        sut.save(new CommentNotification("1", 2L, NotificationType.LIKE, now, now, ninetyDaysAfter, null));
//
//        Optional<Notification> optionalNotification = sut.findById("2");
//
//        Notification notification = optionalNotification.orElseThrow();
//        assertEquals(notification.id, "2");
//        assertEquals(notification.userId, 2L);
//        assertEquals(notification.notificationType, NotificationType.COMMENT);
//        assertEquals(notification.createdAt.getEpochSecond(), now.getEpochSecond());
//        assertEquals(notification.deletedAt.getEpochSecond(), ninetyDaysAfter.getEpochSecond());
//    }
//
//
//
//    @DisplayName("저장 후 삭제")
//    @Test
//    void test_delete_by_id() {
//        sut.save(new Notification("3", 3L, NotificationType.COMMENT, now, ninetyDaysAfter));
//        sut.deleteById("3");
//
//        Optional<Notification> optionalNotification = sut.findById("3");
//        assertFalse(optionalNotification.isPresent());
//    }
//}