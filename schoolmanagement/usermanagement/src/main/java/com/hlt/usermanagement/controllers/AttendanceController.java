package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.services.AttendanceService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/attendance") // Using /v1/attendance for consistency
@Slf4j
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * POST/PUT: Records or updates a single attendance record.
     * Accessible by teachers/admins.
     * API supports the teacher/admin view to mark attendance.
     */
    @PostMapping("/record")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<StandardResponse<AttendanceDTO>> saveAttendance(
            @Valid @RequestBody AttendanceDTO attendanceDTO) {

        log.info("Request received to save/update attendance for student: {}", attendanceDTO.getStudentId());

        // saveAttendance() handles both CREATE (HTTP 201) and UPDATE (HTTP 200).
        // Since we cannot easily differentiate in a single POST method using StandardResponse,
        // we'll return HTTP 200/OK and a success message.
        AttendanceDTO savedDTO = attendanceService.saveAttendance(attendanceDTO);

        log.info("Attendance saved successfully for ID: {}", savedDTO.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.ATTENDANCE_SAVE_SUCCESS,
                savedDTO
        ));
    }

    /**
     * GET: Fetches attendance records for an entire class on a specific date (Teacher/Admin View).
     * Supports the "6th Grade B Section" and date filter UI.
     */
    @GetMapping("/class/{classId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<List<AttendanceDTO>>> getAttendanceByClassAndDate(
            @PathVariable Long classId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        log.info("Request received to fetch attendance for class {} on date {}", classId, date);

        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByClassAndDate(classId, date);

        log.info("Fetched {} attendance records for class {} on {}", attendanceList.size(), classId, date);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.ATTENDANCE_FETCH_SUCCESS,
                attendanceList
        ));
    }

    /**
     * GET: Fetches a specific student's attendance record for a single day (Student/Parent View).
     * Supports the student calendar view.
     */
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_PARENT', 'ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<StandardResponse<AttendanceDTO>> getAttendanceByStudentAndDate(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        log.info("Request received to fetch single attendance record for student {} on {}", studentId, date);

        AttendanceDTO dto = attendanceService.getAttendanceByStudentAndDate(studentId, date);

        log.info("Single attendance record fetched for student {} on {}", studentId, date);
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.ATTENDANCE_FETCH_SUCCESS,
                dto
        ));
    }

    /**
     * GET: Calculates and returns the monthly attendance percentage for a student.
     * Supports the percentage bar visible in the student view.
     */
    @GetMapping("/student/{studentId}/percentage")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_PARENT', 'ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<StandardResponse<Double>> getMonthlyAttendancePercentage(
            @PathVariable Long studentId,
            @RequestParam int month,
            @RequestParam int year) {

        log.info("Request received to calculate monthly attendance percentage for student {} for {}-{}", studentId, month, year);

        Double percentage = attendanceService.calculateMonthlyAttendancePercentage(studentId, month, year);

        log.info("Calculated percentage: {:.2f}%", percentage);
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.ATTENDANCE_PERCENTAGE_SUCCESS,
                percentage
        ));
    }
}
