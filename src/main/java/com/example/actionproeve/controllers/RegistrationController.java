// package com.example.actionproeve.controllers;

// import com.example.actionproeve.entities.Employee;
// import com.example.actionproeve.services.EmployeeService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api")
// public class RegistrationController {

//     @Autowired
//     private EmployeeService employeeService;

//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody Employee employee) {
//         employeeService.saveEmployee(employee); // Save the new employee
//         return ResponseEntity.ok("Employee registered successfully");
//     }
// }
