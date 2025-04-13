package com.assistor.backend.service;

import com.assistor.backend.constants.SignupConstants;
import com.assistor.backend.entity.Student;
import com.assistor.backend.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignupConstants signupConstants;

    public StudentService(StudentRepository studentRepository,
                          PasswordEncoder passwordEncoder, SignupConstants signupConstants) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.signupConstants = signupConstants;
    }

    public String registerStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            return signupConstants.EMAIL_ALREADY_EXISTS ;
        }
        String hashedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(hashedPassword);

        studentRepository.save(student);
        return signupConstants.SIGNUP_SUCCESSFUL;
    }
}


