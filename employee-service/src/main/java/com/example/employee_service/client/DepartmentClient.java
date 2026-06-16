package com.example.employee_service.client;

import com.example.employee_service.entity.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface DepartmentClient {
    @GetExchange("/department/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) ;
}
