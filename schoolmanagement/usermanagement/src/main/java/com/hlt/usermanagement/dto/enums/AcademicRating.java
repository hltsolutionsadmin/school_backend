package com.hlt.usermanagement.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator; // <-- Import required
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AcademicRating {

    POOR("Poor", 1),
    GOOD("Good", 2),
    EXCELLENT("Excellent", 3);

    private final String description;
    private final int value; // The integer value to be used for sorting/storage

    AcademicRating(String description, int value) {
        this.description = description;
        this.value = value;
    }

    /**
     * Used by Jackson for serialization (Java Object -> JSON String).
     * Ensures the output is the description (e.g., "Good").
     */
    @JsonValue
    public String getDescription() {
        return description;
    }

    /**
     * Used by Jackson for deserialization (JSON String -> Java Object).
     * Jackson uses this static factory method to match the input string
     * (e.g., "Good") to the correct Enum constant.
     */
    @JsonCreator
    public static AcademicRating fromDescription(String description) {
        if (description == null) {
            return null;
        }
        for (AcademicRating rating : AcademicRating.values()) {
            if (rating.description.equalsIgnoreCase(description)) { // Using equalsIgnoreCase for better tolerance
                return rating;
            }
        }
        throw new IllegalArgumentException("Invalid AcademicRating description: " + description);
    }


    public static AcademicRating fromValue(int value) {
        for (AcademicRating rating : AcademicRating.values()) {
            if (rating.value == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown AcademicRating value: " + value);
    }
}