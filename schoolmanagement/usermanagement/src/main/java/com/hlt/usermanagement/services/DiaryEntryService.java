package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.DiaryEntryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryEntryService {

    DiaryEntryDTO createDiaryEntry(DiaryEntryDTO dto);

    DiaryEntryDTO updateDiaryEntry(Long id, DiaryEntryDTO dto);

    Page<DiaryEntryDTO> getDiaryEntriesByAcademic(Long academicId, Pageable pageable);

    DiaryEntryDTO getDiaryEntryById(Long id);

    void deleteDiaryEntry(Long id);
}
