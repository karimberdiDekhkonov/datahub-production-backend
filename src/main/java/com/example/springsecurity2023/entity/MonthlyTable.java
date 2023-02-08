package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)

public class MonthlyTable extends UUIDEntity {

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
}
