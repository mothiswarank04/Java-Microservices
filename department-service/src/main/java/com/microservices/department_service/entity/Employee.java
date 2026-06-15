package com.microservices.department_service.entity;

public record Employee(Long id, Long departmentId, String name,Integer age, String position) {
}
