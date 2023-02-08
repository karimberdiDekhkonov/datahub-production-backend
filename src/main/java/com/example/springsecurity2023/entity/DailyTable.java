package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class DailyTable extends UUIDEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private MonthlyTable monthlyTable;

    @Column(nullable = false)
    private String workingHours;

    @Column(nullable = false)
    private float duration;

    @Column(nullable = false)
    private float tip;

    @Column(nullable = false)
    private String tipReason;

    @Column(nullable = false)
    private float penalty;

    @Column(nullable = false)
    private String penaltyReason;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private boolean confirm;
}
