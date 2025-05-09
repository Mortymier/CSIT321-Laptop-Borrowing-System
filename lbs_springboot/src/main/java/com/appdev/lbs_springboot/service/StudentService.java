package com.appdev.lbs_springboot.service;

import com.appdev.lbs_springboot.entity.StudentEntity;
import com.appdev.lbs_springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService 
{
    @Autowired
    private StudentRepository studentRepository;

    public StudentService()
    {
        super();
    }

    // POST or CREATE
    public StudentEntity addStudent(StudentEntity student) 
    {
        return studentRepository.save(student);
    }

    // GET or READ
    public List<StudentEntity> getAllStudents() 
    {
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> getStudentById(int id) 
    {
        return studentRepository.findById(id);
    }

    public StudentEntity getStudentByEmail(String email)
    {
        return studentRepository.findByEmail(email);
    }

    // Login Student
    public Optional<StudentEntity> loginStudent(String email, String password)
    {
        return studentRepository.findByEmailAndPassword(email, password);
    }

    // Register Student
    public StudentEntity registerStudent(StudentEntity student)
    {
        return studentRepository.save(student);
    }

    // PUT or UPDATE
    public StudentEntity updateStudent(int id, StudentEntity updatedStudent) 
    {
        StudentEntity student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setPassword(updatedStudent.getPassword());
        student.setNumber(updatedStudent.getNumber());
        student.setCourse(updatedStudent.getCourse());
        student.setYear(updatedStudent.getYear());

        return studentRepository.save(student);
    }

    // DELETE
    public void deleteStudent(int id) 
    {
        StudentEntity student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student); // Delete the student only if found
    }
}
