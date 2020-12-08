package com.example.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, String> {
    List<Employee> findByEmpNameStartingWith(String empName);
}