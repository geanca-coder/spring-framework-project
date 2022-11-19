package com.geanca.Springbootdemo.repository;

import com.geanca.Springbootdemo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("Mechanical Engineering").departmentCode("232").departmentId(1L).departmentAddress("California, USA").build();
        entityManager.persist(department);
    }

    @Test
    @DisplayName("TEST CASE DepartmentRepository Layer")
    @Disabled
    public void WhenFindByIdThenReturnDepartment(){
        Department department =departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "Mechanical Engineering");
    }
}