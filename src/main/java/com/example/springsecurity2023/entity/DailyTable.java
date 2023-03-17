package com.example.springsecurity2023.entity;

import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
@Entity
@EqualsAndHashCode(callSuper = true)

public class DailyTable extends UUIDEntity {

    //AVOIDING FROM ANNOTATIONS (@Data, @NoArgsConstructor, @AllArgsConstructor).

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

    public DailyTable() {
    }

    public DailyTable(MonthlyTable monthlyTable, String workingHours, float duration, float tip, String tipReason, float penalty, String penaltyReason, int day, boolean confirm) {
        this.monthlyTable = monthlyTable;
        this.workingHours = workingHours;
        this.duration = duration;
        this.tip = tip;
        this.tipReason = tipReason;
        this.penalty = penalty;
        this.penaltyReason = penaltyReason;
        this.day = day;
        this.confirm = confirm;
    }

    public MonthlyTable getMonthlyTable() {
        return monthlyTable;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public float getDuration() {
        return duration;
    }

    public float getTip() {
        return tip;
    }

    public String getTipReason() {
        return tipReason;
    }

    public float getPenalty() {
        return penalty;
    }

    public String getPenaltyReason() {
        return penaltyReason;
    }

    public int getDay() {
        return day;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setMonthlyTable(MonthlyTable monthlyTable) {
        this.monthlyTable = monthlyTable;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setTip(float tip) {
        this.tip = tip;
    }

    public void setTipReason(String tipReason) {
        this.tipReason = tipReason;
    }

    public void setPenalty(float penalty) {
        this.penalty = penalty;
    }

    public void setPenaltyReason(String penaltyReason) {
        this.penaltyReason = penaltyReason;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
