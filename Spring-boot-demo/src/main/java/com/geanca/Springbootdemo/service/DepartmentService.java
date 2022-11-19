package com.geanca.Springbootdemo.service;

import com.geanca.Springbootdemo.entity.Department;
import com.geanca.Springbootdemo.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentById(Long deparmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long id);

    Department updateDepartment(Long id, Department department);

    List<Department> fetchDepartmentByName(String departmentName);
}
