package com.example.springapiwithsecuritydevelopment.repositories;

import com.example.springapiwithsecuritydevelopment.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends CrudRepository<Course, Integer> {}


