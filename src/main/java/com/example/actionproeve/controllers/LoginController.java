package com.example.actionproeve.controllers;

import com.example.actionproeve.entities.Employee;
import com.example.actionproeve.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {

        Employee newEmployee = new Employee();
newEmployee.setUsername("supermario");
newEmployee.setPassword("adminpass1"); // Set plain text password
employeeService.createEmployee(newEmployee);

        try {
            Employee employee = employeeService.login(loginRequest.getUsername(), loginRequest.getPassword());
            session.setAttribute("employee", employee);

            // Create and return the response
            LoginResponse loginResponse = new LoginResponse("Session created", employee);
            return ResponseEntity.ok(loginResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}


class LoginRequest {
    private String username;
    private String password;

    // Default constructor
    public LoginRequest() {}

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

 class LoginResponse {
    private String message; // Changed from token to a message
    private Employee employee;

    // Constructor
    public LoginResponse(String message, Employee employee) {
        this.message = message;
        this.employee = employee;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public Employee getEmployee() {
        return employee;
    }
}
