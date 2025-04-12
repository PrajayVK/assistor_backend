package com.assistor.backend.controller;



import com.assistor.backend.Repository.StudentRepository;
import com.assistor.backend.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            return "Email already registered";
        }
        studentRepository.save(student);
        return "Signup successful";
    }
}