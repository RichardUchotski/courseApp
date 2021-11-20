package com.example.springapiwithsecuritydevelopment.service;

import com.example.springapiwithsecuritydevelopment.model.Lesson;
import com.example.springapiwithsecuritydevelopment.model.Student;
import com.example.springapiwithsecuritydevelopment.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public Iterable<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentsById(Integer studentId) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find student by ID, must not exist in the database");
        }

        return studentOptional.get();
    }

    public void deleteAll() {
        studentRepo.deleteAll();
    }

    public void deleteById(Integer studentId) {
        Optional<Student> studentOptional =  studentRepo.findById(studentId);

        if(studentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find student by ID, must not exist in the database");
        }

        studentRepo.deleteById(studentId);
    }

    public void save(Student student) {
        studentRepo.save(student);
    }

    public void changeStudent(Student student, Integer studentId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);

        if(optionalStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find id to match");
        }

        Student studentToChange = optionalStudent.get();
        studentToChange.setStudentFirstName(student.getStudentFirstName());
        studentToChange.setStudentLastName(student.getStudentLastName());
        studentToChange.setStudentBirthDay(student.getStudentBirthDay());
        studentToChange.setAge(student.getAge());
        studentRepo.save(studentToChange);

    }
}
