package com.example.springapiwithsecuritydevelopment.repositories;

import com.example.springapiwithsecuritydevelopment.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends CrudRepository<Subject, Integer> {}
