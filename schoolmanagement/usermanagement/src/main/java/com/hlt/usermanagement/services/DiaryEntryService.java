package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.DiaryEntryDTO;

import java.util.List;

public interface DiaryEntryService {

    DiaryEntryDTO createDiaryEntry(DiaryEntryDTO dto);

    DiaryEntryDTO getDiaryEntry(Long id);

    List<DiaryEntryDTO> getAllDiaryEntries();

    DiaryEntryDTO updateDiaryEntry(Long id, DiaryEntryDTO dto);

    void deleteDiaryEntry(Long id);

}
