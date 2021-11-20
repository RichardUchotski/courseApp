package com.example.springapiwithsecuritydevelopment.service;

import com.example.springapiwithsecuritydevelopment.model.Lesson;
import com.example.springapiwithsecuritydevelopment.repositories.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    LessonRepo lessonRepo;

    public Iterable<Lesson> getLessons() {
        return lessonRepo.findAll();
    }

    public Lesson getLessonById(Integer lessonId) {
        Optional<Lesson> lessonOptional = lessonRepo.findById(lessonId);

        if(lessonOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find lesson by ID, must not exist in the database");
        }

        return lessonOptional.get();
    }

    public void deleteAll() {
        lessonRepo.deleteAll();
    }

    public void deleteById(Integer lessonId) {
        Optional<Lesson> lessonOptional =  lessonRepo.findById(lessonId);

        if(lessonOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find lesson by ID, must not exist in the database");
        }

        lessonRepo.deleteById(lessonId);
    }

    public void save(Lesson lesson) {
        lessonRepo.save(lesson);
    }

    public void changeLesson(Lesson lesson, Integer lessonId) {
        Optional<Lesson> optionalLesson = lessonRepo.findById(lessonId);

        if(optionalLesson.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Can not find lesson Id, it must not exist in the database");
        }

        Lesson lessonToChange = optionalLesson.get();
        lessonToChange.setLessonLength(lesson.getLessonLength());
        lessonToChange.setLessonName(lesson.getLessonName());
        lessonRepo.save(lessonToChange);
    }
}
