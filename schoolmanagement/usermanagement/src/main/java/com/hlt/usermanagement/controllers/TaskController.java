package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.TaskDTO;
import com.hlt.usermanagement.dto.enums.TaskType;
import com.hlt.usermanagement.services.TaskService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public StandardResponse<TaskDTO> createTask(@RequestBody @Valid TaskDTO dto) {
        return StandardResponse.single("Task created successfully", taskService.createTask(dto));
    }

    @PutMapping("/{id}")
    public StandardResponse<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO dto) {
        return StandardResponse.single("Task updated successfully", taskService.updateTask(id, dto));
    }

    @GetMapping("/{id}")
    public StandardResponse<TaskDTO> getTaskById(@PathVariable Long id) {
        return StandardResponse.single("Task fetched successfully", taskService.getTaskById(id));
    }

    @GetMapping
    public StandardResponse<Page<TaskDTO>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) TaskType type) {

        return StandardResponse.page("Tasks fetched successfully", taskService.getAllTasks(page, size, type));
    }

    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return StandardResponse.message("Task deleted successfully");
    }
}
