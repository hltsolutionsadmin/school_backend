package com.hlt.usermanagement.dto.enums;

public enum EducationLevel {
    PRIMARY("Primary School"),
    MIDDLE("Middle School"),
    SECONDARY("Secondary School"),
    HIGH_SCHOOL("High School"),
    UNDERGRADUATE("Undergraduate"),
    POSTGRADUATE("Postgraduate"),
    DOCTORATE("Doctorate");

    private final String displayName;

    EducationLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
