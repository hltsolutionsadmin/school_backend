package com.hlt.usermanagement.utils;

public final class SchoolAppConstants {

    private SchoolAppConstants() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }


    public static final String SCHOOL_CREATE_SUCCESS = "School created successfully";
    public static final String SCHOOL_FETCH_SUCCESS  = "School fetched successfully";
    public static final String SCHOOL_UPDATE_SUCCESS = "School updated successfully";
    public static final String SCHOOL_DELETE_SUCCESS = "School deleted successfully";

    public static final String CLASS_CREATE_SUCCESS = "class created successfully";
    public static final String CLASS_UPDATE_SUCCESS = "class updated successfully";
    public static final String CLASS_FETCH_SUCCESS = "class fetched successfully";
    public static final String CLASS_DELETE_SUCCESS = "class deleted successfully";
    public static final String STUDENT_FETCH_SUCCESS = "student fetch successfully";
    public static final String SUBJECT_FETCH_SUCCESS = "subject fetch successfully";

    // ========================= Diary Entry Messages =========================
    public static final String DIARY_ENTRY_CREATE_SUCCESS = "Diary entry created successfully.";
    public static final String DIARY_ENTRY_UPDATE_SUCCESS = "Diary entry updated successfully.";
    public static final String DIARY_ENTRY_FETCH_SUCCESS  = "Diary entry fetched successfully.";
    public static final String DIARY_ENTRY_LIST_FETCH_SUCCESS = "Diary entries fetched successfully.";
    public static final String DIARY_ENTRY_DELETE_SUCCESS = "Diary entry deleted successfully.";
    public static final String DIARY_ENTRY_NOT_FOUND = "Diary entry not found.";



    public static final String HOMEWORK_CREATE_SUCCESS = "Homework created successfully";
    public static final String HOMEWORK_FETCH_SUCCESS = "Homework fetched successfully";
    public static final String HOMEWORK_UPDATE_SUCCESS = "Homework updated successfully";
    public static final String HOMEWORK_DELETE_SUCCESS = "Homework deleted successfully";
    public static final String HOMEWORK_LIST_FETCH_SUCCESS = "Homework list fetched successfully";

    public static final String STUDENT_CREATE_SUCCESS = "Student created successfully";
    public static final String STUDENT_UPDATE_SUCCESS = "Student updated successfully";
    public static final String STUDENT_DELETE_SUCCESS = "Student deleted successfully";
    public static final String STUDENT_ENROLL_SUCCESS = "Student enrolled successfully";
    public static final String STUDENT_FETCH_GRADES_SUCCESS = "Student grades fetched successfully";
    public static final String STUDENT_FETCH_ATTENDANCE_SUCCESS = "Student attendance fetched successfully";

    public static final String TEACHER_SUBJECTS_FETCH_SUCCESS = "teacher subjects fetch successfully";
    public static final String TEACHER_CLASSES_FETCH_SUCCESS = "teacher classes fetch successfully";
    public static final String TEACHER_DELETE_SUCCESS = "teacher deleted successfully";
    public static final String TEACHER_FETCH_SUCCESS = "teacher fetched successfully";
    public static final String TEACHER_UPDATE_SUCCESS = "teacher updated successfully";
    public static final String TEACHER_CREATE_SUCCESS = "teacher created successfully";

    public static final String SUBJECT_FETCH_BY_CLASS_SUCCESS = "Subject fetch by class successfully";
    public static final String SUBJECT_DELETE_SUCCESS = "Subject deleted successfully";
    public static final String SUBJECT_UPDATE_SUCCESS = "Subject updated successfully";
    public static final String SUBJECT_CREATE_SUCCESS = "Subject created successfully";


    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String NOT_FOUND = "Requested resource not found";


    public static final String SCHOOL = "School";
    public static final String STUDENT = "Student";
    public static final String TEACHER = "Teacher";
    public static final String STAFF = "Staff";
    public static final String PRINCIPAL = "Principal";
    public static final String ADMIN = "Admin";

    public static final String BASE_API = "/api/v1";
    public static final String SCHOOL_API = BASE_API + "/schools";
    public static final String STUDENT_API = BASE_API + "/students";
    public static final String TEACHER_API = BASE_API + "/teachers";
    public static final String STAFF_API = BASE_API + "/staff";


    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ROLE_PRINCIPAL   = "ROLE_PRINCIPAL";
    public static final String ROLE_ADMIN       = "ROLE_ADMIN";
    public static final String ROLE_TEACHER     = "ROLE_TEACHER";
    public static final String ROLE_STUDENT     = "ROLE_STUDENT";
    public static final String ROLE_STAFF       = "ROLE_STAFF";


    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;
    public static final String SORT_BY_ID = "id";


    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
