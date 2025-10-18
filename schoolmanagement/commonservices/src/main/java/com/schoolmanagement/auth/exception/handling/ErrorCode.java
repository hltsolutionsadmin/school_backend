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
    RESULT_NOT_FOUND(1003, "Result not found", HttpStatus.NOT_FOUND),
    ASSIGNMENT_NOT_FOUND(2001, "Academic resource assignment not found", HttpStatus.NOT_FOUND),
    COMMUNICATION_NOT_FOUND(1002, "Communication not found", HttpStatus.NOT_FOUND),

    INVALID_BUSINESS_TYPE(1003, "Invalid Business Type code", HttpStatus.BAD_REQUEST),
    ACADEMIC_NOT_FOUND(2001, "Academic unit not found", HttpStatus.NOT_FOUND),
    TASK_NOT_FOUND(3001, "Task not found", HttpStatus.NOT_FOUND),
    EVENT_NOT_FOUND(2001, "Academic event not found", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_IN_USE(1002, "Email Is Already In Use", HttpStatus.CONFLICT),
    UNAUTHORIZED(1003, "Unauthorized Access", HttpStatus.UNAUTHORIZED),
    INVALID_ROLE(1007, "Invalid role provided", HttpStatus.BAD_REQUEST),
    FEES_NOT_FOUND(2001, "Fee details not found", HttpStatus.NOT_FOUND),
    TIMETABLE_NOT_FOUND(1005, "Exam timetable not found", HttpStatus.NOT_FOUND),
    EXAM_NOT_FOUND(1002, "Exam not found", HttpStatus.NOT_FOUND),
    REPORT_CARD_NOT_FOUND(1004, "Report card not found", HttpStatus.NOT_FOUND),
    SUBJECT_NOT_FOUND(6001, "Subject not found", HttpStatus.NOT_FOUND),
    OTP_EXPIRED(1801, "OTP Expired", HttpStatus.BAD_REQUEST),
    TOKEN_PROCESSING_ERROR(1804, "Error Processing Refresh Token", HttpStatus.INTERNAL_SERVER_ERROR),
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
