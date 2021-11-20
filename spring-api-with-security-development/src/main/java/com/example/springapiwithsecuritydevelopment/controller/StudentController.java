package com.example.springapiwithsecuritydevelopment.controller;

import com.example.springapiwithsecuritydevelopment.model.Lesson;
import com.example.springapiwithsecuritydevelopment.model.Student;
import com.example.springapiwithsecuritydevelopment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("students")
    public Iterable<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("students/{studentId}")
    public Student getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudentsById(studentId);
    }

    @DeleteMapping("students")
    public void deleteAll(){
        studentService.deleteAll();
    }

    @DeleteMapping("students/{studentId}")
    public void deleteById(@PathVariable Integer studentId) {
        studentService.deleteById(studentId);
    }

    @PostMapping("students")
    public void addStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping("students/{studentId}")
    public void changeStudent(@RequestBody Student student, @PathVariable Integer studentId) {
        studentService.changeStudent(student, studentId);
    }
}
