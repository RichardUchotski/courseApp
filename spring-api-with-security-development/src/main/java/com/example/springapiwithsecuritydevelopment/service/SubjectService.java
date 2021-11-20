package com.example.springapiwithsecuritydevelopment.service;

import com.example.springapiwithsecuritydevelopment.model.Student;
import com.example.springapiwithsecuritydevelopment.model.Subject;
import com.example.springapiwithsecuritydevelopment.repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepo subjectRepo;

    public Iterable<Subject> findAllSubjects() {
        return subjectRepo.findAll();
    }

    public Subject getSubjectById(Integer subjectId) {
        Optional<Subject> subjectOptional = subjectRepo.findById(subjectId);

        if (subjectOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find subject by ID, must not exist in the database");
        }

        return subjectOptional.get();
    }

    public void deleteAll() {
        subjectRepo.deleteAll();
    }

    public void deleteById(Integer subjectId) {
        Optional<Subject> subjectOptional =  subjectRepo.findById(subjectId);

        if(subjectOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find subject by ID, must not exist in the database");
        }

        subjectRepo.deleteById(subjectId);
    }

    public void save(Subject subject) {
        subjectRepo.save(subject);
    }

    public void changeSubject(Subject subject, Integer subjectId) {
        Optional<Subject> subjectOptional = subjectRepo.findById(subjectId);

        if(subjectOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find matching Id");
        }

        Subject subjectToChange = subjectOptional.get();
        subjectToChange.setSubjectName(subject.getSubjectName());
        subjectRepo.save(subjectToChange);
    }
}
