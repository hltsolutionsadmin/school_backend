package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Table(name = "PERSONAL_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FATHER_NAME", length = 100)
    private String fatherName;

    @Column(name = "MOTHER_NAME", length = 100)
    private String motherName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "BLOOD_GROUP", length = 5)
    private String bloodGroup;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "MOBILE_NUMBER", length = 20)
    private String mobileNumber;
}