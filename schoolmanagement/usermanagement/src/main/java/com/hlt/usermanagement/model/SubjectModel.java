package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subjects")
@Getter
@Setter
public class SubjectModel extends GenericModel {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b2b_unit_id", nullable = false)
    private B2BUnitModel b2bUnit;
}
