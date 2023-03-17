package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)

public class Holiday extends UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

    @ManyToOne
    private User ownerId;

    @ManyToOne
    private User employeeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String date;

    public Holiday(User ownerId, User employeeId, String name, String date) {
        this.ownerId = ownerId;
        this.employeeId = employeeId;
        this.name = name;
        this.date = date;
    }

    public Holiday() {
    }

    public User getOwnerId() {
        return ownerId;
    }

    public User getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public void setEmployeeId(User employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
