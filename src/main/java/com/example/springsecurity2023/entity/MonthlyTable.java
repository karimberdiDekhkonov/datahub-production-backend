package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)

public class MonthlyTable extends UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

    @Column(nullable = false)
    private String month;

    private float totalHours;

    private float totalSalary;

    @ManyToOne(fetch = FetchType.EAGER)
    private User employee;

    @ManyToOne(fetch=FetchType.EAGER)
    private MainTable mainTable;

    public MonthlyTable(String month, User employee, MainTable mainTable) {
        this.month = month;
        this.employee = employee;
        this.mainTable = mainTable;
    }

    public MonthlyTable() {
    }

    public MonthlyTable(String month, float totalHours, float totalSalary, User employee, MainTable mainTable) {
        this.month = month;
        this.totalHours = totalHours;
        this.totalSalary = totalSalary;
        this.employee = employee;
        this.mainTable = mainTable;
    }

    public String getMonth() {
        return month;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public float getTotalSalary() {
        return totalSalary;
    }

    public User getEmployee() {
        return employee;
    }

    public MainTable getMainTable() {
        return mainTable;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public void setTotalSalary(float totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public void setMainTable(MainTable mainTable) {
        this.mainTable = mainTable;
    }
}
