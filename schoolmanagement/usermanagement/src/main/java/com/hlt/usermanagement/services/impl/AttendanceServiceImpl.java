package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.model.AttendanceModel;
import com.hlt.usermanagement.populator.AttendancePopulator;
import com.hlt.usermanagement.repository.AttendanceRepository;
import com.hlt.usermanagement.repository.SchoolCalendarRepository;
import com.hlt.usermanagement.services.AttendanceService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendancePopulator attendancePopulator;
    private final SchoolCalendarRepository schoolCalendarRepository; // Assuming this is now injected


    @Override
    @Transactional
    public AttendanceDTO saveAttendance(AttendanceDTO dto) {
        if (dto.getStudentId() == null || dto.getDate() == null) {
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Student ID and Date are required for attendance.");
        }

        AttendanceModel existing = attendanceRepository.findByStudent_IdAndDate(dto.getStudentId(), dto.getDate());

        AttendanceModel entity;
        if (existing != null) {
            log.info("Updating attendance for student {} on {}", dto.getStudentId(), dto.getDate());
            attendancePopulator.updateEntity(dto, existing);
            entity = existing;
        } else {
            log.info("Creating new attendance record for student {} on {}", dto.getStudentId(), dto.getDate());
            entity = attendancePopulator.toEntity(dto);
        }

        AttendanceModel saved = attendanceRepository.save(entity);
        return attendancePopulator.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByClassAndDate(Long classId, LocalDate date) {
        log.info("Fetching attendance for class {} on date {}", classId, date);

        List<AttendanceModel> entities = attendanceRepository.findByStudent_ClassModel_IdAndDate(classId, date);
        return entities.stream()
                .map(attendancePopulator::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AttendanceDTO getAttendanceByStudentAndDate(Long studentId, LocalDate date) {
        log.info("Fetching single attendance record for student {} on date {}", studentId, date);

        AttendanceModel entity = attendanceRepository.findByStudent_IdAndDate(studentId, date);

        if (entity == null) {
            return AttendanceDTO.builder().studentId(studentId).date(date).present(false).build();
        }
        return attendancePopulator.toDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Double calculateMonthlyAttendancePercentage(Long studentId, int month, int year) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        Long presentDays = attendanceRepository.countByStudent_IdAndDateBetweenAndPresentTrue(
                studentId, startDate, endDate
        );

        Long totalInstructionalDays = schoolCalendarRepository.countByDateBetweenAndIsInstructionalTrue(
                startDate, endDate
        );

        if (totalInstructionalDays == null || totalInstructionalDays == 0) {
            log.warn("No instructional days found for {}-{} for student {}", month, year, studentId);
            return 0.0;
        }

        double percentage = ((double) presentDays / totalInstructionalDays) * 100.0;

        log.info("Calculated attendance for student {} for {}-{}: {}/{} = {:.2f}%",
                studentId, month, year, presentDays, totalInstructionalDays, percentage);

        return Math.round(percentage * 100.0) / 100.0;
    }
}