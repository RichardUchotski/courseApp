package com.example.springapiwithsecuritydevelopment.controller;

import com.example.springapiwithsecuritydevelopment.model.Lesson;
import com.example.springapiwithsecuritydevelopment.model.Subject;
import com.example.springapiwithsecuritydevelopment.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("subjects")
    public Iterable<Subject> getAllSubjects() {
        return subjectService.findAllSubjects();
    }

    @GetMapping("subjects/{subjectId}")
    public Subject getSubjectById(@PathVariable Integer subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @DeleteMapping("subjects")
    public @ResponseBody void deleteAll() {
        subjectService.deleteAll();
    }

    @DeleteMapping("subjects/{subjectId}")
    public void deleteById(@PathVariable Integer subjectId) {
        subjectService.deleteById(subjectId);
    }

    @PostMapping("subjects")
    public void addSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
    }

    @PutMapping("subjects/{subjectId}")
    public void changeSubject(@RequestBody Subject subject, @PathVariable Integer subjectId) {
        subjectService.changeSubject(subject, subjectId);
    }
}

