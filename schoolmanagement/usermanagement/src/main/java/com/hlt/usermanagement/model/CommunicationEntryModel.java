package com.hlt.usermanagement.model;
import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "communications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationEntryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel createdBy;

    @Enumerated(EnumType.STRING)
    private CommunicationType type;

    private String title;

    @Column(length = 2000)
    private String content;

    @Enumerated(EnumType.STRING)
    private CommunicationStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}