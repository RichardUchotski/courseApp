package com.example.springapiwithsecuritydevelopment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter @Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column @NotBlank
    private String subjectName;

    // ! non-owning side of the relationship cannot be added in the api client as a field for this class
    // One subject to many courses (non-owning side, when delete the foreign key needs to be deleted or removed)
    @OneToMany(
            mappedBy = "subject",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JsonIgnoreProperties("subject")
    List<Course> courseList;

    //  ?When run, this will go through the courseList and set every subject to null
    @PreRemove
    private void removeCoursesFromTeacher() {
        for (Course c : courseList) {
            c.setSubject(null);
        }
    }git

    // ? Adding constructors
    public Subject() {
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }



}
