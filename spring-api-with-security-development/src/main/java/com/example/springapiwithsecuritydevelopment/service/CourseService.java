package com.example.springapiwithsecuritydevelopment.service;

import com.example.springapiwithsecuritydevelopment.model.Course;
import com.example.springapiwithsecuritydevelopment.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;

    public Iterable<Course> getCourses() {
        return courseRepo.findAll();
    }

    public Course getCoursesById(Integer courseId) {
        Optional<Course> courseOptional =  courseRepo.findById(courseId);

        if(courseOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find course by ID, must not exist in the database");
        }

        return courseOptional.get();
    }

    public void deleteAll() {
        courseRepo.deleteAll();
    }

    public void deleteById(Integer courseId) {
        Optional<Course> courseOptional =  courseRepo.findById(courseId);

        if(courseOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find course by ID, must not exist in the database");
        }

        courseRepo.deleteById(courseId);
    }

    public void save(Course course) {
        courseRepo.save(course);
    }


    public void editACourse(Course course, Integer courseId) {
        Optional<Course> optionalCourse = courseRepo.findById(courseId);

        if(optionalCourse.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id of your course does not match one in the database");
        }

        Course courseToChange = optionalCourse.get();
        courseToChange.setCourseName(course.getCourseName());
        courseToChange.setCourseStartDate(course.getCourseStartDate());
        courseToChange.setCourseEndDate(course.getCourseEndDate());
        courseRepo.save(courseToChange);
    }
}
