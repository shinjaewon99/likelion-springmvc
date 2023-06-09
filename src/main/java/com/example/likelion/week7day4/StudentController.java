package com.example.likelion.week7day4;

import com.example.likelion.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }


    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("email")
            String email
    ){
        StudentDto studentDto = studentService.makeNewStudent(name, email);

        System.out.println(studentDto.toString());
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("studentList", studentService.readStudentAll());

        return "read";
    }

    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id, Model model) {

        model.addAttribute(
                "student",
                studentService.readStudent(id)
        );
        return "readStudent";
    }
}
