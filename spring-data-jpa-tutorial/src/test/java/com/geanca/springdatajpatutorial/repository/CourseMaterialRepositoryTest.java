package com.geanca.springdatajpatutorial.repository;

import com.geanca.springdatajpatutorial.entity.Course;
import com.geanca.springdatajpatutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){

        Course course = Course.builder()
                .title("Software Engineering I")
                .credit(5)
                .build();

        CourseMaterial material = CourseMaterial.builder()
                .url("www.facebook.com")
                .course(course)
                .build();
        
        repository.save(material);
    }
    @Test
    public  void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println(courseMaterials);
    }
}