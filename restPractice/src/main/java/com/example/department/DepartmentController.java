package com.example.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="depts")
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/find1/{deptid}")
	public Optional<Department> getDepartment1(
		@PathVariable("deptid") String deptid) {
		return departmentRepository.findById(deptid);
	}

	@GetMapping("/find2/{deptid}")
	public Optional<Department> getDepartment2(@PathVariable("deptid") String deptid) {
		Optional<Department> optDept = departmentRepository.findById(deptid);
		// NOTE : 조회는 성공, 성공 후 다 null 값으로 바꾸어버리는 코드.
		if (optDept.isPresent()) {
			Department dept = optDept.get();
			for (int i=0; i < dept.getEmployees().size(); i++) {
				dept.getEmployees().get(i).setDepartment(null);
			}
		}
		return optDept;
	}
}
