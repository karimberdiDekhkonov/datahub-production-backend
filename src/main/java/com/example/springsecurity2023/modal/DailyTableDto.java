package com.example.springsecurity2023.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTableDto {
    private UUID monthlyTableId;
    private String workingHours;
    private float duration;
    private float tip;
    private String tipReason;
    private float penalty;
    private String penaltyReason;
    private int day;
    private boolean confirm;
    private float salary;
}
