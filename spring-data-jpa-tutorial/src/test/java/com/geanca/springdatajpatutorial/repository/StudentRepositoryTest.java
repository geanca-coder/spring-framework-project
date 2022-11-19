package com.geanca.springdatajpatutorial.repository;

import com.geanca.springdatajpatutorial.entity.Guardian;
import com.geanca.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("geancarlosberm54@gmail.com")
                .firstName("Geancarlo")
                .lastName("Bermudez")
//                .guardianEmail("Diana@gmail.com")
//                .guardianMobile("32937232")
//                .guardianName("Diana")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("diana@gmail.com")
                .name("diana")
                .mobile("212121")
                .build();
        Student student = Student.builder()
                .firstName("geancarlo")
                .emailId("geanca@gmail.com")
                .lastName("bermudez")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }
    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("Geancarlo");
        System.out.println(studentList);
    }

    @Test
    public void printStudentByMatchingWithName(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("car");
        System.out.println(studentList.size());
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("diana");
        System.out.println(students);

    }
    @Test
    public  void printNotNullLastNameRecords(){
        List<Student> students = studentRepository.findByLastNameNotNull();
        System.out.println(students);
    }

    @Test
    public void printRecordMatchingFirstNameAndLastName(){
        Student student = studentRepository.findByFirstNameAndLastName("geancarlo", "bermudez");
        System.out.println(student);
    }


    @Test
    public  void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("geancarlosberm54@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printGetStudentNameByEmailAddress(){
        String student = studentRepository.getStudentNameByEmailAddress("geancarlosberm54@gmail.com");
        System.out.println(student);
    }

    @Test
    public  void printGetStudentNameByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("geancarlosberm54@gmail.com");
        System.out.println(student);
    }

    @Test
    public  void printGetStudentNameByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("geancarlosberm54@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailIdNamedParamTest(){
        studentRepository.updateStudentNameByEmailIdNamedParam("Jose",
                "geancarlosberm54@gmail.com");
    }


}