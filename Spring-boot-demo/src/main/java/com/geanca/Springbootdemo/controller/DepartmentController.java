package com.geanca.Springbootdemo.controller;

import com.geanca.Springbootdemo.entity.Department;
import com.geanca.Springbootdemo.error.DepartmentNotFoundException;
import com.geanca.Springbootdemo.service.DepartmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER =  LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody  Department department){
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        LOGGER.info("Inside saveDeparment of DeparmentController");
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id")  Long deparmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside saveDeparment of DeparmentController");
        return departmentService.fetchDepartmentById(deparmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDeparmentById(@PathVariable("id") Long id){
        departmentService.deleteDepartmentById(id);
        return "Department deleted succesfully";

    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department){
        LOGGER.info("Inside saveDeparment of DeparmentController");
        return departmentService.updateDepartment(id, department);
    }

    @GetMapping("/departments/name/{departmentName}")
    public List<Department> fetchDepartmentByName(@PathVariable String departmentName){
        LOGGER.info("Inside saveDeparment of DeparmentController");
        return departmentService.fetchDepartmentByName(departmentName);
    }

}
