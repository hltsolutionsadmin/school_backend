package com.schoolmanagement.auth.exception.handling;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // ===========================
    // User & Auth Errors (1000–1099)
    // ===========================
    USER_NOT_FOUND(1000, "User Not Found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(1001, "User Already Exists", HttpStatus.CONFLICT),
    BUSINESS_NOT_FOUND(1002, "Business not found", HttpStatus.NOT_FOUND),
    HOMEWORK_NOT_FOUND(2001, "Homework not found", HttpStatus.NOT_FOUND),
    HOMEWORK_ALREADY_EXISTS(2002, "Homework already exists", HttpStatus.BAD_REQUEST),
    INVALID_BUSINESS_TYPE(1003, "Invalid Business Type code", HttpStatus.BAD_REQUEST),

    EMAIL_ALREADY_IN_USE(1002, "Email Is Already In Use", HttpStatus.CONFLICT),
    UNAUTHORIZED(1003, "Unauthorized Access", HttpStatus.UNAUTHORIZED),
    INVALID_ROLE(1007, "Invalid role provided", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_OPERATION(1016, "Unauthorized operation", HttpStatus.UNAUTHORIZED),
    PROFILE_INCOMPLETE(1010, "Student profile not completed. Please complete your profile before applying.", HttpStatus.BAD_REQUEST),
    INVALID_ROLE_FOR_OPERATION(1017, "Invalid role for this operation", HttpStatus.FORBIDDEN),
    SCHOOL_NOT_FOUND(2001, "School not found", HttpStatus.NOT_FOUND),
    SCHOOL_ALREADY_EXISTS(2002, "School already exists", HttpStatus.BAD_REQUEST),
    SCHOOL_CODE_ALREADY_IN_USE(2003, "School code already in use", HttpStatus.BAD_REQUEST),

    // Class related errors
    CLASS_NOT_FOUND(3001, "Class not found", HttpStatus.NOT_FOUND),
    CLASS_ALREADY_EXISTS(3002, "Class already exists", HttpStatus.BAD_REQUEST),

    // Student related errors
    STUDENT_NOT_FOUND(4001, "Student not found", HttpStatus.NOT_FOUND),
    STUDENT_ALREADY_EXISTS(4002, "Student already exists", HttpStatus.BAD_REQUEST),

    // Teacher related errors
    TEACHER_NOT_FOUND(5001, "Teacher not found", HttpStatus.NOT_FOUND),
    TEACHER_ALREADY_EXISTS(5002, "Teacher already exists", HttpStatus.BAD_REQUEST),

    // Subject related errors
    SUBJECT_NOT_FOUND(6001, "Subject not found", HttpStatus.NOT_FOUND),
    SUBJECT_ALREADY_EXISTS(6002, "Subject already exists", HttpStatus.BAD_REQUEST),

    // Parent/Guardian
    PARENT_NOT_FOUND(7001, "Parent not found", HttpStatus.NOT_FOUND),

    // Staff related errors
    STAFF_NOT_FOUND(8001, "Staff not found", HttpStatus.NOT_FOUND),
    STAFF_ALREADY_EXISTS(8002, "Staff already exists", HttpStatus.BAD_REQUEST),
    // ===========================
    // OTP & Token (1800–1899)
    // ===========================
    OTP_EXPIRED(1801, "OTP Expired", HttpStatus.BAD_REQUEST),
    TOKEN_PROCESSING_ERROR(1804, "Error Processing Refresh Token", HttpStatus.INTERNAL_SERVER_ERROR),

    // ===========================
    // Address & App Info (1900–1999)
    // ===========================
    ADDRESS_NOT_FOUND(1901, "Address not found.", HttpStatus.NOT_FOUND),
    INVALID_ADDRESS(1902, "Invalid address data or unauthorized access.", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(1903, "Access denied —  ownership mismatch for the given user ID.", HttpStatus.BAD_REQUEST),

    // ===========================
    // General Exceptions (2000–2099)
    // ===========================
    NOT_FOUND(2000, "Requested Resource Not Found", HttpStatus.NOT_FOUND),
    BAD_REQUEST(2000, "Bad Request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(2001, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FORBIDDEN(2002, "Forbidden", HttpStatus.FORBIDDEN),
    METHOD_NOT_ALLOWED(2003, "Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED),
    NULL_POINTER(2004, "Null Pointer Exception", HttpStatus.BAD_REQUEST),

    // Product, Category, Business (3000–3099)
    // ===========================
    CATEGORY_NOT_FOUND(3001, "Category not found", HttpStatus.NOT_FOUND),
    ALREADY_EXISTS(3003, "Resource already exists", HttpStatus.CONFLICT),
    ROLE_NOT_FOUND(3004, "Role not found", HttpStatus.CONFLICT),
    DUPLICATE_TEACHER_FOR_USER(3005, "Duplicate teacher for user", HttpStatus.CONFLICT),
    INVALID_INPUT(3006, "Student ID must be provided for a grade.", HttpStatus.CONFLICT);

    // ===========================
    // Fields
    // ===========================
    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }
}
