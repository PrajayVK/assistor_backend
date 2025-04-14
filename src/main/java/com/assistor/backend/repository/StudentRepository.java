package com.assistor.backend.repository;

import com.assistor.backend.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    boolean existsByEmail(String email);

    Optional<Student> findByEmail(String email);
}

