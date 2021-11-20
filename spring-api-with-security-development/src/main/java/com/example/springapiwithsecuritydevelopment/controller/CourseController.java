package com.example.springapiwithsecuritydevelopment.controller;

import com.example.springapiwithsecuritydevelopment.model.Course;
import com.example.springapiwithsecuritydevelopment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("courses")
    public Iterable<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("courses/{courseId}")
    public Course getCourseById(@PathVariable Integer courseId) {
        return courseService.getCoursesById(courseId);
    }

    @DeleteMapping("courses")
    public String deleteAllCourses() {
        courseService.deleteAll();
        return "you have deleted all the courses";
    }

    @DeleteMapping("courses/{courseId}")
    public void deleteCourseById(@PathVariable Integer courseId) {
        courseService.deleteById(courseId);
        System.out.println("You deleted the course with the ID of " + courseId);
    }

    @PostMapping("courses")
    public void addACourse(@RequestBody Course course) {
        courseService.save(course);
    }

    @PutMapping("courses/{courseId}")
    public void editACourse(@RequestBody Course course, @PathVariable Integer courseId) {
        courseService.editACourse(course, courseId);
    }







}
