package com.assistor.backend.service;

import com.assistor.backend.constants.SignupConstants;
import com.assistor.backend.entity.Student;
import com.assistor.backend.repository.StudentRepository;
import com.assistor.backend.validation.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public boolean validEmail(String email) {
        return  emailValidator.validEmail(email);
    }
    
    public boolean validRequest(Student student){
        return validEmail(student.getEmail());
    }
}


