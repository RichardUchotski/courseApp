package com.example.springapiwithsecuritydevelopment.model;

import com.fasterxml.jackson.annotation.*;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter @Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column @NotBlank
    private String studentFirstName;

    @Column @NotBlank
    private String studentLastName;

    @Column(unique = true) @NotBlank
    private String email;

    @Column @NotBlank @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column @NotBlank @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String passwordConfirmation;

    @Column @DateTimeFormat @Past
    private LocalDate studentBirthDay;

    // TODO: Work out how to set the date from the age and get it to work in springBoot
    @Column @Min(18)
    private Integer age;

    // TODO: Add a front end where you can enlist students, teachers, courses and lessons and link them together
    // * owning side the relationship, a course must have students, but perhaps these can be added with a method - so no forced validation for data entry
    // Many students to many course
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    )
    @JsonIgnoreProperties("studentList")
    @NotNull @JsonManagedReference // ? we are showing in the API response, what courses the students are one
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id") // ? Serializes the property by the ID
    @JsonIdentityReference(alwaysAsId = true) // ? This makes the property only return the id in the API
    // TODO: need to get this down to just the course name and id
    private List<Course> courseList;

    // Adding constructors
    public Student() {
    }

    public Student(String studentFirstName, String studentLastName, String email, String password, String passwordConfirmation, LocalDate studentBirthDay, Integer age) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.studentBirthDay = studentBirthDay;
        this.age = age;
    }

}


