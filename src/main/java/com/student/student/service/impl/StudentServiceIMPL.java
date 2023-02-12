package com.student.student.service.impl;

import com.student.student.dto.StudentDTO;
import com.student.student.entity.StudentEntity;
import com.student.student.repository.StudentRepo;
import com.student.student.service.StudentService;
import com.student.student.util.VarList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceIMPL implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String saveStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDTO,studentEntity);

        if(studentRepo.existsById(studentDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            studentRepo.save(studentEntity);
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public String updateStudent(StudentDTO studentDTO) {
        if(!studentRepo.existsById(studentDTO.getId())){
            return VarList.RSP_NO_DATA_FOUND;
        }else {
            StudentEntity studentEntity = new StudentEntity();
            BeanUtils.copyProperties(studentDTO,studentEntity);
            studentRepo.save(studentEntity);
            return VarList.RSP_SUCCESS;
        }
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentEntity> studentEntities = studentRepo.findAll();
        List<StudentDTO> studentDTOS = studentEntities
                .stream()
                .map(stu -> new StudentDTO(
                        stu.getId(),
                        stu.getName(),
                        stu.getAddress(),
                        stu.getPhoneNumber()
                )).collect(Collectors.toList());
        return studentDTOS;
    }

    @Override
    public boolean deleteStudent(long id) {

        if(studentRepo.existsById(id)){
            StudentEntity studentEntity = studentRepo.findById(id).get();
            studentRepo.delete(studentEntity);
            return true;
        }else {
            return false;
        }
    }
}
