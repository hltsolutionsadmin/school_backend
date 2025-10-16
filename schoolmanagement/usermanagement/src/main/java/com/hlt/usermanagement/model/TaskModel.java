package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.TaskType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskModel extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_TYPE", nullable = false)
    private TaskType taskType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_id", nullable = false)
    private AcademicModel academic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiated_by_user_id", nullable = false)
    private UserModel initiatedBy;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = true;

    @Column(name = "ATTACHMENT_URL")
    private String attachmentUrl;

    @Column(name = "PRIORITY")
    private Integer priority;
}
