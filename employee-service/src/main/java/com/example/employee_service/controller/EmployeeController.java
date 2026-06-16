package com.example.employee_service.controller;

import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/getall")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getDepartmentById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createDepartment(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
}
