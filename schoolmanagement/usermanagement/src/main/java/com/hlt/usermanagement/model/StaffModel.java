package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.DepartmentType;
import com.hlt.usermanagement.dto.enums.StaffType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STAFF_TYPE", nullable = false, length = 50)
    private StaffType staffType;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTMENT", length = 100)
    private DepartmentType department;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private B2BUnitModel school;
}
