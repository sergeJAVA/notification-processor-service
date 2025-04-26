package com.example.notification_processor_service.service;

import com.example.notification_processor_service.model.Notification;
import com.example.notification_processor_service.model.NotificationEvent;

import java.util.List;

public interface NotificationService {
    Notification createNotification(NotificationEvent event);

    List<Notification> sendNotificationsByUserId(String userId);

    Notification getNotificationById(String notificationId);
}
