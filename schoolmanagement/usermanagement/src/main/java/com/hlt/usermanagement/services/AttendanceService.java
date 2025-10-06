package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AttendanceDTO;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceDTO saveAttendance(AttendanceDTO dto);

    List<AttendanceDTO> getAttendanceByClassAndDate(Long classId, LocalDate date);

    AttendanceDTO getAttendanceByStudentAndDate(Long studentId, LocalDate date);

    Double calculateMonthlyAttendancePercentage(Long studentId, int month, int year);
}
