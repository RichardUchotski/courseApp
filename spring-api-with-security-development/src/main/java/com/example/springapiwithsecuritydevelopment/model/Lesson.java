package com.example.springapiwithsecuritydevelopment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalTime;

@Entity
@Table(name = "lessons")
@Getter @Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column @NotBlank
    private String lessonName;

    @Column @NotNull
    private LocalTime lessonLength;

    // * Owning side of the relationship, needs to be put in the API client as a field
    // Many lessons to one course - owning side of the relationship, can be added as an argument in the API client
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("lessonList")
    @NotNull
    private Course course;

    // ? Adding constructors
    public Lesson() {
    }

    public Lesson(String lessonName, LocalTime lessonLength) {
        this.lessonName = lessonName;
        this.lessonLength = lessonLength;
    }
}
