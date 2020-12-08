package com.example.employee;

import com.example.department.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@JsonInclude(Include.NON_NULL)
@ToString(exclude = {"department"})
@NoArgsConstructor
public class Employee {
    @Id
    @Column(name="EMP_ID")
    private String id;
    private String empName;
    private String phone;

    // NOTE : restAPI 에서는 직렬화 할 때 연관관계 다 생성해줘야하므로 EAGER를 쓴다.
    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonIgnore // NOTE : 한쪽이 참조를 못하게 되는 현상이 발생한다.
    @JoinColumn(name="DEPT_ID", nullable = false)
    private Department department;

    public Employee(String id, String empName, String phone) {
        this.id = id;
        this.empName = empName;
        this.phone = phone;
    }
}
