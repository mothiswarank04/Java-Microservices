package com.example.employee_service.controller;

import com.example.employee_service.client.DepartmentClient;
import com.example.employee_service.entity.Department;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentClient departmentClient;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/getall")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getDepartmentById(@PathVariable Long id) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = employeeOptional.get();
        Department department = departmentClient.getDepartmentById(employee.getDepartmentId()).getBody();

        if (department != null) {
            employee.setDepartmentName(department.name());
        }

        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createDepartment(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }
}
