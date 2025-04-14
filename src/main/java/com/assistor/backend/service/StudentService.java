package com.assistor.backend.service;

import com.assistor.backend.dto.Request.LoginRequest;
import com.assistor.backend.entity.Student;
import com.assistor.backend.repository.StudentRepository;
import com.assistor.backend.validation.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.assistor.backend.constants.LoginConstants.*;
import static com.assistor.backend.constants.SignupConstants.*;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;

    public String registerStudent(Student student) {
        if(validRequest(student)){
            if (studentRepository.existsByEmail(student.getEmail())) {
                return EMAIL_ALREADY_EXISTS;
            }
            String hashedPassword = passwordEncoder.encode(student.getPassword());
            student.setPassword(hashedPassword);

            studentRepository.save(student);
            return SIGNUP_SUCCESSFUL;
        }
        return INVALID_REQUEST;
    }

    public String retrieveStudent(LoginRequest loginRequest)  {
        if(validRequest(loginRequest)){
            Optional<Student> studentOptional = studentRepository.findByEmail(loginRequest.getEmail());
            Student student;
            if(studentOptional.isPresent()){
                student = studentOptional.get();
                if(passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())){
                    return LOGIN_SUCCESSFUL;
                } else {
                    return INCORRECT_PASSWORD;
                }
            }
            return EMAIL_DOES_NOT_EXIST;
        }
        return INVALID_REQUEST;
    }
    public boolean validEmail(String email) {
        return  emailValidator.validEmail(email);
    }
    
    public boolean validRequest(Student student){
        return validEmail(student.getEmail());
    }
    public boolean validRequest(LoginRequest loginRequest){
        return validEmail(loginRequest.getEmail());
    }
}


