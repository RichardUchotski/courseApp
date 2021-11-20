package com.example.springapiwithsecuritydevelopment.controller;

import com.example.springapiwithsecuritydevelopment.model.Teacher;
import com.example.springapiwithsecuritydevelopment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("teachers")
    public Iterable<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("teachers/{teacherId}")
    public Teacher getTeacherById(@PathVariable Integer teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @DeleteMapping("teachers")
    public void deleteAll() {
        teacherService.deleteAll();
    }

    @DeleteMapping("teachers/{teacherId}")
    public void deleteById(@PathVariable Integer teacherId) {
        teacherService.deleteById(teacherId);
    }

    @PostMapping("teachers")
    public void addSubject(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
    }

    @PutMapping("teachers/{teacherId}")
    public void changeTeacher(@RequestBody Teacher teacher, @PathVariable Integer teacherId) {
        teacherService.changeTeacher(teacher, teacherId);
    }

}
