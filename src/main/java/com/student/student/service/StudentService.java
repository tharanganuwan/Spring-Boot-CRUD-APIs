package com.student.student.service;

import com.student.student.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    String saveStudent(StudentDTO studentDTO);

    String updateStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudent();

    boolean deleteStudent(long id);

}
