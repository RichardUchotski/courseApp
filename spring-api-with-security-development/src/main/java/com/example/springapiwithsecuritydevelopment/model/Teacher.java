package com.example.springapiwithsecuritydevelopment.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column(nullable = false) @NotNull
    private LocalDate birthday;

    @Column
    private Integer age;

    // * Owning side of the relationship - must be added in API
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnoreProperties("teacherList")
    @NotNull // TODO: Check this out later, as it may stop the front end from adding a teacher to a course, maybe do not have this now, or work out how to get this down
    @JsonManagedReference // ? Currently, shows the courses a teacher runs in the API response List
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id") // ? Serializes the property by the ID
    @JsonIdentityReference(alwaysAsId = true) // ? This makes the property only return the id in the API
    private List<Course> courseList;

    // Adding constructors
    public Teacher() {
    }

    public Teacher(String firstName, String secondName, LocalDate birthday, Integer age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.age = age;
    }
}
