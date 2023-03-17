package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)

public class Schedule extends UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

    @OneToOne
    private User employeeId;

    @Column(nullable = false)
    private String monday;

    @Column(nullable = false)
    private String tuesday;

    @Column(nullable = false)
    private String wednesday;

    @Column(nullable = false)
    private String thursday;

    @Column(nullable = false)
    private String friday;

    @Column(nullable = false)
    private String saturday;

    @Column(nullable = false)
    private String sunday;

    public Schedule() {
    }

    public Schedule(User employeeId, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.employeeId = employeeId;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public User getEmployeeId() {
        return employeeId;
    }

    public String getMonday() {
        return monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public String getFriday() {
        return friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setEmployeeId(User employeeId) {
        this.employeeId = employeeId;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
}
