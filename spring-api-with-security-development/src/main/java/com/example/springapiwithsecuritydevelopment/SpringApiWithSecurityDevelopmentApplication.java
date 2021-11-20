package com.example.springapiwithsecuritydevelopment;

import com.example.springapiwithsecuritydevelopment.model.*;
import com.example.springapiwithsecuritydevelopment.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class SpringApiWithSecurityDevelopmentApplication {

	public static void main(String[] args) { SpringApplication.run(SpringApiWithSecurityDevelopmentApplication.class, args); }

		@Bean
		protected CommandLineRunner commandLineRunner( SubjectRepo subjectRepo, CourseRepo courseRepo, LessonRepo lessonRepo, StudentRepo studentRepo, TeacherRepo teacherRepo)  {
			return args -> {
				// Creating and saving Subjects
				Subject biology = new Subject("Biology");
				Subject chemistry  = new Subject("Chemistry");
				subjectRepo.save(biology);
				subjectRepo.save(chemistry);

				// Creating Courses
				Course biology101 = new Course("Biology-101", LocalDate.of(2022, 9, 16), LocalDate.of(2025, 8,1));
				Course biology102 = new Course("Biology-102", LocalDate.of(2022, 9, 16), LocalDate.of(2025, 8,1));
				biology101.setSubject(biology);
				biology102.setSubject(biology);
				courseRepo.save(biology101);
				courseRepo.save(biology102);

				// Creating and saving Teachers
				Teacher teacher1 = new Teacher("Rachael", "Tooley", LocalDate.of(1993, 6, 20), 28);
				Teacher teacher2 = new Teacher("Rachael", "Dunne", LocalDate.of(1992, 6, 20), 29);
				teacher1.setCourseList(List.of(biology101, biology102));
				teacher2.setCourseList(List.of(biology101, biology102));
				teacherRepo.save(teacher1);
				teacherRepo.save(teacher2);

				// Creating and Saving Lessons
				Lesson firstLesson = new Lesson("Structure of the Cell", LocalTime.of(3,30, 0));
				Lesson secondLesson = new Lesson("Function of the Cell", LocalTime.of(2,30, 0));
				firstLesson.setCourse(biology101);
				secondLesson.setCourse(biology102);
				lessonRepo.save(firstLesson);
				lessonRepo.save(secondLesson);

				// Creating and saving Students
				Student student1 = new Student("Richard" , "Smith-Uchotski", "rsmithuhcot1@gmail.com", "password123", "password123", LocalDate.of(1994, 1, 28), 27);
				Student student2 = new Student("Ania", "Smith-Uchotska", "anina1@gmail.com", "password123", "password123", LocalDate.of(1998,4,13), 23);
				student1.setCourseList(List.of(biology101, biology102));
				student2.setCourseList(List.of(biology101, biology102));
				studentRepo.save(student1);
				studentRepo.save(student2);
			};
		}
}
