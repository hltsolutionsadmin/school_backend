package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.TeacherDTO;
import com.hlt.usermanagement.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO dto) {
        TeacherDTO createdTeacher = teacherService.createTeacher(dto);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }
}