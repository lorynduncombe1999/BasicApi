package com.basicAPI.basicApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//The services class holds the actual logic of the code that needs to be executed once it is called in the controller
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
       if(studentOptional.isPresent()){
           //"server.error.include-message = always" in application.properties files allows the "email taken
           //error to be shown in the postman error message
           throw new IllegalStateException("email taken");
       }
       studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
       boolean exists =  studentRepository.existsById(studentId);
       if(!exists){
           throw new IllegalStateException("student with id" + studentId + "does not exists");

       }
       studentRepository.deleteById(studentId);
    }
//@Transactional annotation entity goes into a "managed state"
@Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
                "student with id"+ studentId + "does not exist"
        ));
                if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
    }
    if (email != null && name.length() > 0 && !Objects.equals(student.getEmail(), email)){
        student.setEmail(email);
}
}
}
