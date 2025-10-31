package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.TaskDTO;
import com.hlt.usermanagement.model.TaskModel;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.services.UserService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.utils.Populator;
import com.schoolmanagement.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskPopulator implements Populator<TaskModel, TaskDTO> {

    private final AcademicRepository academicRepository;
    private final UserService userService;

    @Override
    public void populate(TaskModel source, TaskDTO target) {
        if (source == null) return;

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setTaskType(source.getTaskType());
        target.setAcademicId(source.getAcademic().getId());
        target.setInitiatedById(source.getInitiatedBy().getId());
        target.setDueDate(source.getDueDate());
        target.setActive(source.getActive());
        target.setAttachmentUrl(source.getAttachmentUrl());
        target.setPriority(source.getPriority());
    }

    public TaskDTO toDTO(TaskModel model) {
        if (model == null) return null;
        TaskDTO dto = new TaskDTO();
        populate(model, dto);
        return dto;
    }

    public TaskModel toModel(TaskDTO dto) {
        if (dto == null) return null;
        AcademicModel academic = academicRepository.findById(dto.getAcademicId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ACADEMIC_NOT_FOUND));

        UserModel initiatedBy = userService.findById(SecurityUtils.getCurrentUserDetails().getId());
        if (initiatedBy == null) {
            throw new HltCustomerException(ErrorCode.USER_NOT_FOUND);
        }

        return TaskModel.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .taskType(dto.getTaskType())
                .academic(academic)
                .initiatedBy(initiatedBy)
                .dueDate(dto.getDueDate())
                .active(dto.getActive())
                .attachmentUrl(dto.getAttachmentUrl())
                .priority(dto.getPriority())
                .build();
    }
}
