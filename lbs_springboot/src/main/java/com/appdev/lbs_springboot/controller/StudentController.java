package com.appdev.lbs_springboot.controller;

import com.appdev.lbs_springboot.entity.StudentEntity;
import com.appdev.lbs_springboot.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/students")
public class StudentController 
{
    @Autowired
    private StudentService studentService;

    // POST or CREATE
    @PostMapping
    public StudentEntity addStudent(@RequestBody StudentEntity student) 
    {
        return studentService.addStudent(student);
    }

    @PostMapping("/login")
    public ResponseEntity<StudentEntity> loginStudent(@RequestParam String email, @RequestParam String password)
    {
        Optional<StudentEntity> student = studentService.loginStudent(email, password);
        return student.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/register")
    public ResponseEntity<StudentEntity> registerStudent(@RequestBody StudentEntity student)
    {
        StudentEntity newStudent = studentService.registerStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    // GET or READ
    @GetMapping
    public List<StudentEntity> getAllStudents() 
    {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable int id) 
    {
        Optional<StudentEntity> student = studentService.getStudentById(id);
        if (student.isPresent()) 
        {
            return ResponseEntity.ok(student.get());
        } 
        else 
        {
            return ResponseEntity.status(404).body(null); // Return 404 if student is not found
        }
    }

    // PUT or UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable int id, @RequestBody StudentEntity student) 
    {
        StudentEntity updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) 
    {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content after successful deletion
    }
}