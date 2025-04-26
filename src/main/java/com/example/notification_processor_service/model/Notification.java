package com.example.notification_processor_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "notifications")
public class Notification {
    @Id
    private String id;
    private String userId;
    private String type; // LIKE, COMMENT
    private String content;
    private String postId;
    private String status; // PENDING, SENT, FAILED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
