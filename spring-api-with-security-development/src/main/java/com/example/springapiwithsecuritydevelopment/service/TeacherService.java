package com.example.springapiwithsecuritydevelopment.service;

import com.example.springapiwithsecuritydevelopment.model.Teacher;
import com.example.springapiwithsecuritydevelopment.repositories.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepo teacherRepo;

    public Iterable<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(Integer teacherId) {
        Optional<Teacher> teacherOptional = teacherRepo.findById(teacherId);

        if (teacherOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find teacher by ID, must not exist in the database");
        }

        return teacherOptional.get();
    }

    public void deleteAll() {
        teacherRepo.deleteAll();
    }

    public void deleteById(Integer teacherId) {
        Optional<Teacher> teacherOptional =  teacherRepo.findById(teacherId);

        if(teacherOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find teacher by ID, must not exist in the database");
        }

        teacherRepo.deleteById(teacherId);
    }

    public void save(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    public void changeTeacher(Teacher teacher, Integer teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepo.findById(teacherId);

        if(optionalTeacher.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Id does not match one in the database");
        }

        Teacher teacherToChange = optionalTeacher.get();
        teacherToChange.setFirstName(teacher.getFirstName());
        teacherToChange.setSecondName(teacher.getSecondName());
        teacherToChange.setBirthday(teacher.getBirthday());
        teacherToChange.setAge(teacher.getAge());
        teacherRepo.save(teacherToChange);
    }
}
