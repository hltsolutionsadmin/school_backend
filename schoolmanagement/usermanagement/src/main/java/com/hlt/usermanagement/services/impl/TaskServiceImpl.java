package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.TaskDTO;
import com.hlt.usermanagement.dto.TaskType;
import com.hlt.usermanagement.model.TaskModel;
import com.hlt.usermanagement.populator.TaskPopulator;
import com.hlt.usermanagement.repository.TaskRepository;
import com.hlt.usermanagement.services.TaskService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskPopulator taskPopulator;

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        TaskModel model = taskPopulator.toModel(dto);
        TaskModel saved = taskRepository.save(model);
        return taskPopulator.toDTO(saved);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        TaskModel existing = taskRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TASK_NOT_FOUND));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setTaskType(dto.getTaskType());
        existing.setDueDate(dto.getDueDate());
        existing.setActive(dto.getActive());
        existing.setAttachmentUrl(dto.getAttachmentUrl());
        existing.setPriority(dto.getPriority());

        TaskModel updated = taskRepository.save(existing);
        return taskPopulator.toDTO(updated);
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        TaskModel model = taskRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TASK_NOT_FOUND));
        return taskPopulator.toDTO(model);
    }

    @Override
    public Page<TaskDTO> getAllTasks(int page, int size, TaskType type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<TaskModel> taskPage;

        if (type != null) {
            taskPage = taskRepository.findByTaskType(type, pageable);
        } else {
            taskPage = taskRepository.findAll(pageable);
        }

        return new PageImpl<>(
                taskPage.getContent().stream().map(taskPopulator::toDTO).collect(Collectors.toList()),
                pageable,
                taskPage.getTotalElements()
        );
    }

    @Override
    public void deleteTask(Long id) {
        TaskModel existing = taskRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TASK_NOT_FOUND));
        taskRepository.delete(existing);
    }
}
