package com.example.actionproeve.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateStartTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String activityName;

    @Column(nullable = false)
    private String custName;

    @Column(nullable = false)
    private String custPhoneNum;

    @Column(nullable = false)
    private int guestAmount;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Constructors
    public Booking() {
    }

    public Booking(LocalDateTime dateStartTime, LocalDateTime endTime, String activityName, String custName, String custPhoneNum, int guestAmount, Employee employee) {
        this.dateStartTime = dateStartTime;
        this.endTime = endTime;
        this.activityName = activityName;
        this.custName = custName;
        this.custPhoneNum = custPhoneNum;
        this.guestAmount = guestAmount;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateStartTime() {
        return dateStartTime;
    }

    public void setDateStartTime(LocalDateTime dateStartTime) {
        this.dateStartTime = dateStartTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhoneNum() {
        return custPhoneNum;
    }

    public void setCustPhoneNum(String custPhoneNum) {
        this.custPhoneNum = custPhoneNum;
    }

    public int getGuestAmount() {
        return guestAmount;
    }

    public void setGuestAmount(int guestAmount) {
        this.guestAmount = guestAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
    

