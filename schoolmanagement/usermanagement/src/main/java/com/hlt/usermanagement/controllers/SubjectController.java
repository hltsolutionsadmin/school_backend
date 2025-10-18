package com.hlt.usermanagement.controllers;


import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.services.SubjectService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public StandardResponse<SubjectDTO> create(@RequestBody SubjectDTO dto) {
        return StandardResponse.single("Subject created successfully", subjectService.createSubject(dto));
    }

    @PutMapping("/{id}")
    public StandardResponse<SubjectDTO> update(@PathVariable Long id, @RequestBody SubjectDTO dto) {
        return StandardResponse.single("Subject updated successfully", subjectService.updateSubject(id, dto));
    }

    @GetMapping("/{id}")
    public StandardResponse<SubjectDTO> getById(@PathVariable Long id) {
        return StandardResponse.single("Subject fetched successfully", subjectService.getSubjectById(id));
    }

    @GetMapping
    public StandardResponse<?> list(@RequestParam Long b2bUnitId, Pageable pageable) {
        return StandardResponse.page("Subjects fetched successfully", subjectService.getSubjects(b2bUnitId, pageable));
    }

    @DeleteMapping("/{id}")
    public StandardResponse<?> delete(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return StandardResponse.message("Subject deleted successfully");
    }
}
