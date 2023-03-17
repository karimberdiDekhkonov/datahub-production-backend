package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
public class Company extends UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch= FetchType.EAGER)
    private User ownerId;

    public Company(String name, User ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    public Company() {

    }

    public String getName() {
        return name;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }
}
