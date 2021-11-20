package com.example.springapiwithsecuritydevelopment.repositories;

import com.example.springapiwithsecuritydevelopment.model.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Integer> {}
