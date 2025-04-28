package com.example.notification_processor_service.controller;

import com.example.notification_processor_service.model.Notification;
import com.example.notification_processor_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification-processor")
public class ProcessorController {
    private final NotificationService notificationService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.sendNotificationsByUserId(userId));
    }

    @GetMapping("/id/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable String notificationId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable String notificationId) {
        notificationService.deleteById(notificationId);
        return ResponseEntity.ok("Notification with "+ notificationId + " has deleted");
    }
}