package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.DiaryEntryDTO;
import com.hlt.usermanagement.services.DiaryEntryService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary-entries")
@RequiredArgsConstructor
public class DiaryEntryController {

    private final DiaryEntryService diaryEntryService;

    /**
     * Create a new diary entry
     */
    @PostMapping
    public StandardResponse<DiaryEntryDTO> createDiaryEntry(@Valid @RequestBody DiaryEntryDTO dto) {
        DiaryEntryDTO created = diaryEntryService.createDiaryEntry(dto);
        return StandardResponse.single("Diary entry created successfully", created);
    }

    /**
     * Update an existing diary entry
     */
    @PutMapping("/{id}")
    public StandardResponse<DiaryEntryDTO> updateDiaryEntry(
            @PathVariable Long id,
            @Valid @RequestBody DiaryEntryDTO dto
    ) {
        DiaryEntryDTO updated = diaryEntryService.updateDiaryEntry(id, dto);
        return StandardResponse.single("Diary entry updated successfully", updated);
    }

    /**
     * Get diary entries by academic with pagination
     */
    @GetMapping("/academic/{academicId}")
    public StandardResponse<Page<DiaryEntryDTO>> getDiaryEntriesByAcademic(
            @PathVariable Long academicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DiaryEntryDTO> entries = diaryEntryService.getDiaryEntriesByAcademic(academicId, pageable);
        return StandardResponse.page("Diary entries retrieved successfully", entries);
    }

    /**
     * Get a single diary entry by ID
     */
    @GetMapping("/{id}")
    public StandardResponse<DiaryEntryDTO> getDiaryEntryById(@PathVariable Long id) {
        DiaryEntryDTO dto = diaryEntryService.getDiaryEntryById(id);
        return StandardResponse.single("Diary entry retrieved successfully", dto);
    }

    /**
     * Delete a diary entry
     */
    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteDiaryEntry(@PathVariable Long id) {
        diaryEntryService.deleteDiaryEntry(id);
        return StandardResponse.message("Diary entry deleted successfully");
    }
}
