package com.example.actionproeve.services;

import com.example.actionproeve.entities.Employee;
import com.example.actionproeve.repositories.EmployeeRepository; // Updated reference

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository; // Updated reference

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username); // Updated reference
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
            employee.getUsername(), 
            employee.getPassword(), 
            new ArrayList<>()); // Add roles/authorities as needed
    }
}
