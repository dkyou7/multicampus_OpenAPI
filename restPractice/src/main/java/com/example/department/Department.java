package com.example.department;

import com.example.employee.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@ToString(exclude = {"employees"})
@NoArgsConstructor
public class Department {
  @Id
  @Column(name="DEPT_ID")
  private String id;
  private String deptName;
  private String location;

  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  private List<Employee> employees = new ArrayList<Employee>();

  public Department(String id, String deptName, String location) {
    this.id = id;
    this.deptName = deptName;
    this.location = location;
  }
}