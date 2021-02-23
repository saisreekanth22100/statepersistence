package com.app.challenge.ppf.statepersistence.repository;


import com.app.challenge.ppf.statepersistence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByContractInformationContains(String name);

}
