package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Entity
@Table(name = "SCHOOL_CALENDAR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolCalendarModel extends GenericModel {

    @Column(name = "CALENDAR_DATE", nullable = false, unique = true)
    private LocalDate date;


    @Column(name = "IS_INSTRUCTIONAL", nullable = false)
    private Boolean isInstructional = Boolean.TRUE;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID", foreignKey = @ForeignKey(name = "FK_CALENDAR_SCHOOL"))
    private SchoolModel school;
}