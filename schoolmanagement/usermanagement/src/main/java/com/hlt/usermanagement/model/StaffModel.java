package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "STAFF",
        indexes = {
                @Index(name = "idx_staff_user", columnList = "USER_ID"),
                @Index(name = "idx_staff_school", columnList = "SCHOOL_ID")
        }
)
@Getter
@Setter
public class StaffModel extends AuditableModel {

    @NotBlank
    @Size(max = 50)
    @Column(name = "STAFF_TYPE", nullable = false)
    private String staffType;  

    @Size(max = 100)
    @Column(name = "DEPARTMENT")
    private String department;  

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    /** Relation to User */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private UserModel user;

    /** Belongs to a school */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private SchoolModel school;
}
