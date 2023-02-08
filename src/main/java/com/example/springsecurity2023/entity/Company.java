package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Company extends UUIDEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch= FetchType.EAGER)
    private User ownerId;
}
