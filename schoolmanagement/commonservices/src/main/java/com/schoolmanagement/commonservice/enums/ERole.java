package com.schoolmanagement.commonservice.enums;

public enum ERole {
    ROLE_USER,
    ROLE_SUPER_ADMIN,
    ROLE_STUDENT,
    ROLE_PRINCIPAL,         // Manages one school
    ROLE_ADMIN,             // Day-to-day school operations
    ROLE_TEACHER,           // Manages subjects, students, grades
    ROLE_PARENT,            // Parent/Guardian linked to student
    ROLE_STAFF,             // Non-teaching staff: transport, library, office
    ROLE_BUSINESS_ADMIN

}

