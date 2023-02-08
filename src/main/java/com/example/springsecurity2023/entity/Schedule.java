package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)

public class Schedule extends UUIDEntity {

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
}
