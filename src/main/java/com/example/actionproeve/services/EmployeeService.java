package com.example.actionproeve.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.actionproeve.entities.Employee;
import com.example.actionproeve.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository; // Assuming this exists

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee createEmployee(Employee employee) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword); // Set the hashed password
        return employeeRepository.save(employee); // Save the employee to the database
    }

    // Example of update method
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setUsername(updatedEmployee.getUsername());
        existingEmployee.setPassword(passwordEncoder.encode(updatedEmployee.getPassword())); // Hashing the new password

        return employeeRepository.save(existingEmployee);
    }

    // Existing login method
    public Employee login(String username, String password) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
            return employee;
        }
        throw new RuntimeException("Invalid username or password");
    }
}
