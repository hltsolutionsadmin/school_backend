package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.TaskDTO;
import com.hlt.usermanagement.dto.enums.TaskType;
import org.springframework.data.domain.Page;

public interface TaskService {
    TaskDTO createTask(TaskDTO dto);

    TaskDTO updateTask(Long id, TaskDTO dto);

    TaskDTO getTaskById(Long id);

    Page<TaskDTO> getAllTasks(int page, int size, TaskType type);

    Page<TaskDTO> getTasksByTeacherId(Long teacherId, int page, int size);

    void deleteTask(Long id);
}
