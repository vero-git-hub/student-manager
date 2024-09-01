package org.example.studentmanager.repository;

import org.example.studentmanager.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
@author vero-git-hub
**/
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}