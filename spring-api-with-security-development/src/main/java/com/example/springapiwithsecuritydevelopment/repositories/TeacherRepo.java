package com.example.springapiwithsecuritydevelopment.repositories;

import com.example.springapiwithsecuritydevelopment.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends CrudRepository<Teacher, Integer> {}
