package com.example.springapiwithsecuritydevelopment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter @Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column @NotBlank
    private String courseName;

    @Column @DateTimeFormat @Future
    private LocalDate courseStartDate;

    @Column @DateTimeFormat @Future
    private LocalDate courseEndDate;

    // ! non-owning side of the relationship cannot be added to the API from the API client for this class
    // One course to many lessons (non owning side - when deleted foreign key needs to be deleted or removed by helper method)
    // Both side of this cascade are merge to prevent the detached entity error.html
    @OneToMany(mappedBy = "course", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonIgnoreProperties("course")
    @JsonBackReference // ? Not Serializing (converting to JSON) the lesson list, so it does not show up on the course API response
    private List<Lesson> lessonList;

    // ! non-owning side of the relationship cannot be added in the api client on the student class as a field for this class
    // Many courses to many Students (non owning side - when deleted foreign key needs to be deleted or removed by helper method)
    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties("courseList")
    @JsonBackReference // ? Not Serializing (converting to JSON) the student list, so it does not show up on the JSON response
    private List<Student> studentList;

    // * Owning side of the relationship, can be added to the API as a field from the API client for this class
    // Subject to course (owning side, when deleted foreign key is deleted)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties("courseList")
    @NotNull
    @JsonManagedReference // ? This means we are serializing this field, so it shows up in the API json response
    private Subject subject;

    // ! non-owning side of the relationship cannot be added in the api client on the student class as a field for this class
    // ManyCourses to Many Teachers (non owning side - when deleted foreign key needs to be deleted or removed by helper method)
    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties("courseList")
    @JsonBackReference // ? We are not serializing the teacher list, so it does not show up in the API response
    private List<Teacher> teacherList;

    // Adding constructors
    public Course() {
    }

    public Course(String courseName, LocalDate courseStartDate, LocalDate courseEndDate) {
        this.courseName = courseName;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }

    // Can only have one pre-remove per-class, these are the methods that remove the foreign key from the owning side of the relationship
    // The pre-remove is an annotated function from JPA Entity Lifecycle events, it is run before an entity is removed from the database. Therefore, every time you delete course,
    // via the method will run and go from every teacher from the courseList, and will remove evey Student from the courseList.
    @PreRemove
    private void removeCoursesFromTeacher() {
        for (Teacher t : teacherList) {
            t.getCourseList().remove(this);
        }

        for (Student s : studentList) {
            s.getCourseList().remove(this);
        }
    }


}
