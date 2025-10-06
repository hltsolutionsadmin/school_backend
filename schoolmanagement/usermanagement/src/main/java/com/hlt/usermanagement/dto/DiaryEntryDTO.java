package com.hlt.usermanagement.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class DiaryEntryDTO {
    private Long id;
    private Long studentId;
    private Long staffId;
    private String subject;
    private String description;
    private LocalDate entryDate;
    private String type;
    private LocalDate dueDate;
    private String status;
    private String submissionNote;
    private String submissionFilePath;

}
