package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DIARY_ENTRY")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class DiaryEntryModel extends AuditableModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private UserModel student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STAFF_ID", nullable = false)
    private StaffModel staff;

    @Column(name = "SUBJECT", nullable = false, length = 100)
    private String subject;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Column(name = "ENTRY_DATE", nullable = false)
    private LocalDate entryDate;
}
