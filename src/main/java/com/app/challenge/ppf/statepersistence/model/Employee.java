package com.app.challenge.ppf.statepersistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long employeeId;
    private String name;
    private String contractInformation;
    private String department;
    private String role;
    @Enumerated(EnumType.STRING)
    private Status status;

   public Employee(Long employeeId,String name, String contractInformation, String department, String role, Status status) {
    }

    public Employee() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(name, employee.name) && Objects.equals(contractInformation, employee.contractInformation) && Objects.equals(department, employee.department) && Objects.equals(role, employee.role) && Objects.equals(status, employee.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, contractInformation, department, role,status);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", contractInformation='" + contractInformation + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", status=" + status +
                '}';
    }
}


