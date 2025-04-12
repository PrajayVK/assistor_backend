package com.assistor.backend.Repository;

import com.assistor.backend.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    boolean existsByEmail(String email);
}
