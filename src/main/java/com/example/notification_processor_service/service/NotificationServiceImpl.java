package com.example.notification_processor_service.service;

import com.example.notification_processor_service.model.Notification;
import com.example.notification_processor_service.model.NotificationEvent;
import com.example.notification_processor_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(NotificationEvent event) {
        if (event == null) {
            throw new RuntimeException("Passed event is null, notification will not be created");
        }

        Notification notification = Notification.builder()
                .id(event.getNotificationId())
                .userId(event.getUserId())
                .type(event.getType())
                .content(event.getContent())
                .postId(event.getPostId())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> sendNotificationsByUserId(String userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        notifications.forEach(notification -> {
            notification.setUpdatedAt(LocalDateTime.now());
            notification.setStatus("SENT");
        });
        return notificationRepository.saveAll(notifications);
    }

    @Override
    public Notification getNotificationById(String notificationId) {
        return notificationRepository.findById(notificationId).orElse(null);
    }


}
