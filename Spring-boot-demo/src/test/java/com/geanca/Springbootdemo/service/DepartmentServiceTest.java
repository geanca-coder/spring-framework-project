package com.geanca.Springbootdemo.service;

import com.geanca.Springbootdemo.entity.Department;
import com.geanca.Springbootdemo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("IT").departmentCode("268032").departmentId(1L).departmentAddress("Costa Rica").build();
        List<Department> departmentList = new ArrayList<Department>();
        departmentList.add(department);
        Mockito.when(departmentRepository.findAllByDepartmentNameIgnoreCase("IT")).thenReturn(departmentList);
    }

    @Test
    @DisplayName("GET DATA BASED ON VALID DEPARTMENT NAME")
    @Disabled
    public void whenValidDepartmentNameThenDepartmentShouldFound(){
        String departmentName = "IT";
        Department found = (Department) departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}