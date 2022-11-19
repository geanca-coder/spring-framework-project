package com.geanca.springdatajpatutorial.repository;

import com.geanca.springdatajpatutorial.entity.Course;
import com.geanca.springdatajpatutorial.entity.Student;
import com.geanca.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .lastName("Bermudez")
                .firstName("Blandon")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(5)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPagewithThreeRecords =
                 PageRequest.of(0,3);

        Pageable secondPageWithTwoRecords =   PageRequest.of(1,2);


        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords)
                .getContent();
        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println(courses);

        System.out.println(totalElements);

        System.out.println(totalPages);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0,2, Sort.by("title"));

        Pageable sortByCredit =
                PageRequest.of(0,2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit"))

                );
        List<Course> courses
                = courseRepository.findAll(sortByTitle).getContent();
        System.out.println(courses);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);
        List<Course> courses =
                courseRepository.findByTitleContaining("P",firstPageTenRecords).getContent();
        long totalElements =
                courseRepository.findByTitleContaining("P",firstPageTenRecords).getTotalElements();

        System.out.println(totalElements);
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Oldemar")
                .lastName("Rodriguez")
                .build();

        Student student  = Student.builder()
                .firstName("diana")
                .lastName("saenz")
                .emailId("dianasaenzLeon@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(4)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}