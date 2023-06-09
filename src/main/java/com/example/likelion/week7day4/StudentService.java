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

    // Service에서 단일 StudentDto를 주는 메소드를 만들것입니다.

    public StudentDto readStudent(Long id) {
        // TODO
        // 여기는 자바 코드 (추후에 변경)
        for (StudentDto studentDto : studentDtoList) {
            if(studentDto.getId().equals(id)){
                return studentDto;
            }
        }
        return null;

//        return studentDtoList
//                .stream()
//                .filter(studentDto -> studentDto.getId().equals(id))
//                .findFirst()
//                .orElse(null);
    }

}
