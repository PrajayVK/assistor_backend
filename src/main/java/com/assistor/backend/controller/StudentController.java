package com.assistor.backend.controller;

import com.assistor.backend.dto.Request.LoginRequest;
import com.assistor.backend.dto.Response.SignupResponse;
import com.assistor.backend.entity.Student;
import com.assistor.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assistor.backend.constants.LoginConstants.EMAIL_DOES_NOT_EXIST;
import static com.assistor.backend.constants.LoginConstants.INCORRECT_PASSWORD;
import static com.assistor.backend.constants.SignupConstants.EMAIL_ALREADY_EXISTS;
import static com.assistor.backend.constants.SignupConstants.INVALID_REQUEST;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody Student student) {
        String result = studentService.registerStudent(student);

        if (EMAIL_ALREADY_EXISTS.equals(result)) {
            return ResponseEntity.status(400).body(new SignupResponse(false, result, student.getEmail()));
        }
        if(INVALID_REQUEST.equals(result)){
            return ResponseEntity.status(400).body(new SignupResponse(false, result, student.getEmail()));
        }

        return ResponseEntity.ok(new SignupResponse(true, result, student.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<SignupResponse> login(@RequestBody LoginRequest loginRequest) {
        String result = studentService.retrieveStudent(loginRequest);
        if(INVALID_REQUEST.equals(result)){
            return ResponseEntity.status(400).body(new SignupResponse(false, result, loginRequest.getEmail()));
        } if(INCORRECT_PASSWORD.equals(result)){
            return ResponseEntity.status(401).body(new SignupResponse(false, result, loginRequest.getEmail()));
        } if (EMAIL_DOES_NOT_EXIST.equals(result)){
            return ResponseEntity.status(404).body(new SignupResponse(false, result, loginRequest.getEmail()));
        }
        return ResponseEntity.ok(new SignupResponse(true, result, loginRequest.getEmail()));

    }
}