package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.HomeworkDTO;
import org.springframework.data.domain.Page;

public interface HomeworkService {

    HomeworkDTO createHomework(HomeworkDTO homeworkDTO);

    HomeworkDTO updateHomework(Long id, HomeworkDTO homeworkDTO);

    HomeworkDTO getHomeworkById(Long id);

    Page<HomeworkDTO> getAllHomework(int page, int size);

    void deleteHomework(Long id);
}
