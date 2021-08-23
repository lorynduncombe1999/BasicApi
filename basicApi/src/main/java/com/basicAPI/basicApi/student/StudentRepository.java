package com.basicAPI.basicApi.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Repository responsible for data access layer
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //SELECT * FROM student Where email = ?
    Optional<Student> findStudentByEmail(String email);
}
