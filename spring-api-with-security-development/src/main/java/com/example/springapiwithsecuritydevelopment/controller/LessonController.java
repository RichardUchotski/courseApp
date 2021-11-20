package com.example.springapiwithsecuritydevelopment.controller;


import com.example.springapiwithsecuritydevelopment.model.Lesson;
import com.example.springapiwithsecuritydevelopment.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping("lessons")
    public Iterable<Lesson> getLessons() {
        return lessonService.getLessons();
    }

    @GetMapping("lessons/{lessonId}")
    public Lesson getLessonById(@PathVariable Integer lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @DeleteMapping("lessons")
    public void deleteAll() {
        lessonService.deleteAll();
    }

    @DeleteMapping("lessons/{lessonId}")
    public void deleteById(@PathVariable Integer lessonId) {
        lessonService.deleteById(lessonId);
    }

    @PostMapping("lessons")
    public void addLesson(@Valid @RequestBody Lesson lesson) {
        lessonService.save(lesson);
    }

    @PutMapping("lessons/{lessonId}")
    public void changeLesson(@RequestBody Lesson lesson, @PathVariable Integer lessonId) {
        lessonService.changeLesson(lesson, lessonId);
    }

}
