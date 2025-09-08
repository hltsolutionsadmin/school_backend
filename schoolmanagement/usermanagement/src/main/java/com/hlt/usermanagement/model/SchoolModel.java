package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "SCHOOL",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"CODE"})
        },
        indexes = {
                @Index(name = "idx_school_code", columnList = "CODE", unique = true),
                @Index(name = "idx_school_name", columnList = "NAME")
        }
)
@Getter
@Setter
public class SchoolModel extends AuditableModel {

    @NotBlank
    @Size(max = 100)
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @Size(max = 20)
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRINCIPAL_ID")
    private UserModel principal;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private AddressModel address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserModel> users = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassModel> classes = new ArrayList<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffModel> staff = new ArrayList<>();

}
