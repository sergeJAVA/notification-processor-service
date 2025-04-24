package com.example.notification_processor_service.processor;

import com.example.notification_processor_service.model.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "notifications-topic", groupId = "notification-group")
    public void processNotification(NotificationEvent event) {
        try {
            // Отправляем уведомление конкретному пользователю через WebSocket
            messagingTemplate.convertAndSend("/topic/notifications/user/" + event.getUserId(), event);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process notification", e);
        }
    }
}
