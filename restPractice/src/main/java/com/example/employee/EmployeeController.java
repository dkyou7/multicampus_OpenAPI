package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="emps")
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository; 
	
	@GetMapping("find1/{name}")
	public List<Employee> getDepartment1(
		@PathVariable("name") String name) {
		return employeeRepository.findByEmpNameStartingWith(name);  
	}
	@GetMapping("find2/{name}")
	public List<Employee> getDepartment2(
			@PathVariable("name") String name) {
		List<Employee> employeeList = employeeRepository.findByEmpNameStartingWith(name);
		if (employeeList.size() > 0) {
			for (Employee e : employeeList) {
				e.getDepartment().setEmployees(null);
			}
		}
		return employeeList;
	}
}