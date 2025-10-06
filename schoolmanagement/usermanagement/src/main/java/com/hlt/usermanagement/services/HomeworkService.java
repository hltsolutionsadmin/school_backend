
package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.HomeworkDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HomeworkService {

    // Create a new homework
    HomeworkDTO createHomework(HomeworkDTO homeworkDTO);

    // Update existing homework
    HomeworkDTO updateHomework(Long homeworkId, HomeworkDTO homeworkDTO);

    // Get homework by ID
    HomeworkDTO getHomeworkById(Long homeworkId);

    // Get paginated homework for a student
    Page<HomeworkDTO> getHomeworkByStudent(Long studentId, Pageable pageable);

    // Get all homework for a staff
    List<HomeworkDTO> getHomeworkByStaff(Long staffId);

    // Get paginated homework for a staff
    Page<HomeworkDTO> getHomeworkByStaff(Long staffId, Pageable pageable);

    // Delete homework
    void deleteHomework(Long homeworkId);
}
