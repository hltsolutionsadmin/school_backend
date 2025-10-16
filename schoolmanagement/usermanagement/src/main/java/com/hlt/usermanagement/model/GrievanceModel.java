package com.hlt.usermanagement.model;


import com.hlt.usermanagement.dto.enums.GrievanceStatus;
import com.hlt.usermanagement.dto.enums.GrievanceType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "grievances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrievanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private GrievanceType type;

    @Column(length = 4000)
    private String description;

    @Enumerated(EnumType.STRING)
    private GrievanceStatus status;

    // Optional link to an attachment stored elsewhere (S3/URL)
    private String attachmentUrl;

    // Soft delete flag
    private boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

