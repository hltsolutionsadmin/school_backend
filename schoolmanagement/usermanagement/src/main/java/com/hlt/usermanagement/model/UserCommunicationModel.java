package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a communication record created by a user (complaint, suggestion, notice).
 */
@Entity
@Table(name = "user_communications",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_type_status", columnList = "type, status")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCommunicationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel createdBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationType type;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = CommunicationStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
