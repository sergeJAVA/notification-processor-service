package com.example.notification_processor_service.listener;

import com.example.notification_processor_service.model.NotificationEvent;
import com.example.notification_processor_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics = "notifications-topic", groupId = "notification-group")
    public void processNotification(ConsumerRecord<String, NotificationEvent> consumerRecord) {
        notificationService.createNotification(consumerRecord.value());
    }

}
