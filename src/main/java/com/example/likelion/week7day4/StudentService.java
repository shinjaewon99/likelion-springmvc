package com.example.likelion.week7day4;

import com.example.likelion.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<StudentDto> studentDtoList = new ArrayList<>();
    private Long nextId = 1L;

    public StudentDto makeNewStudent(String name, String email) {
        StudentDto newStudent = new StudentDto(nextId, name, email);
        studentDtoList.add(newStudent);
        nextId++;


        return newStudent;
    }

    public List<StudentDto> readStudentAll(){
        return studentDtoList;
    }

}
