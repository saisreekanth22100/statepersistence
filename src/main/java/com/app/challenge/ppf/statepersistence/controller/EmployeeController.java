package com.app.challenge.ppf.statepersistence.controller;

import com.app.challenge.ppf.statepersistence.model.Employee;
import com.app.challenge.ppf.statepersistence.model.Status;
import com.app.challenge.ppf.statepersistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/challenge")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/hallo")
    public String sayHallo(){

        return "Welcome to StateChange Challenge";
    }

    /**
     * Create employee employee.
     *
     * @param employee the employee
     * @return the employee
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee _employee = employeeRepository
                    .save(new Employee(employee.getEmployeeId(), employee.getName(),
                            employee.getContractInformation(), employee.getDepartment(),
                            employee.getRole(), Status.ADDED));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);

        if (employeeData.isPresent()) {
            Employee employee = employeeData.get();
            employee.setStatus(Status.INCHECK);
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(value = "contractualInfo",required = false) String contractualInfo) {
        try {
            List<Employee> _employees = new ArrayList<Employee>();

            if (contractualInfo == null)
                employeeRepository.findAll().forEach(_employees::add);
            else
                employeeRepository.findByContractInformationContains(contractualInfo).forEach(_employees::add);

            if (_employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(_employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
