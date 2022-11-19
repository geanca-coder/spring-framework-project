package com.geanca.springdatajpatutorial.repository;

import com.geanca.springdatajpatutorial.entity.Course;
import com.geanca.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void saveTeacher(){
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course programmingI = Course.builder()
                .title("programmingI")
                .credit(5)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Gregorio")
                .lastName("Villalobos")
//                .courses(List.of(courseDBA, programmingI))
                .build();

        teacherRepository.save(teacher);
    }

}