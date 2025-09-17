package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.TeacherDTO;

public interface TeacherService {

    /**
     * Creates a new teacher entity from the provided DTO.
     *
     * @param dto The TeacherDTO containing the data for the new teacher.
     * @return The created TeacherDTO with the assigned ID and other details.
     */
    TeacherDTO createTeacher(TeacherDTO dto);
}