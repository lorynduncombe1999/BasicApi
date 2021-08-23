package com.basicAPI.basicApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Used postman to test mapping and function
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    // @Autowired Allows you to use to use the student service class as an object therby allowing you to access its features
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService =  studentService;
    }

    //Retrieves information from the database
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();

    }

    //Post is used when you want to add new information to the system
    //@RequestBody takes in information and maps it to create a new student
    @PostMapping("/registration")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
                studentService.updateStudent(studentId, name, email);
    }

}
