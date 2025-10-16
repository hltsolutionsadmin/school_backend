package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.HomeworkDTO;
import com.hlt.usermanagement.services.HomeworkService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homework")
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;


    @PostMapping
    public StandardResponse<HomeworkDTO> createHomework(@Valid @RequestBody HomeworkDTO homeworkDTO) {
        HomeworkDTO created = homeworkService.createHomework(homeworkDTO);
        return StandardResponse.single("Homework created successfully", created);
    }

    @PutMapping("/{id}")
    public StandardResponse<HomeworkDTO> updateHomework(
            @PathVariable Long id,
            @Valid @RequestBody HomeworkDTO homeworkDTO) {
        HomeworkDTO updated = homeworkService.updateHomework(id, homeworkDTO);
        return StandardResponse.single("Homework updated successfully", updated);
    }


    @GetMapping("/{id}")
    public StandardResponse<HomeworkDTO> getHomeworkById(@PathVariable Long id) {
        HomeworkDTO homework = homeworkService.getHomeworkById(id);
        return StandardResponse.single("Homework fetched successfully", homework);
    }


    @GetMapping
    public StandardResponse<Page<HomeworkDTO>> getAllHomework(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<HomeworkDTO> pagedHomework = homeworkService.getAllHomework(page, size);
        return StandardResponse.page("Homework list fetched successfully", pagedHomework);
    }

    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteHomework(@PathVariable Long id) {
        homeworkService.deleteHomework(id);
        return StandardResponse.message("Homework deleted successfully");
    }
}
