package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(
        name = "academic_resource_assignment",
        indexes = {
                @Index(name = "idx_resource_id", columnList = "resource_id"),
                @Index(name = "idx_subject_id", columnList = "subject_id"),
                @Index(name = "idx_academic_id", columnList = "academic_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicResourceAssignmentModel extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", nullable = false)
    private UserModel resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectModel subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_id", nullable = false)
    private AcademicModel academic;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "academic_resource_availability",
            joinColumns = @JoinColumn(name = "assignment_id")
    )
    private List<ResourceAvailability> availabilitySlots;
}
