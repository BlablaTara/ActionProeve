package com.example.actionproeve.repositories;

import com.example.actionproeve.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username); 
}
