package com.example.springsecurity2023.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyOrTipDto {
    private UUID dailyTableId;
    private UUID monthlyTableId;
    private float tip;
    private String tipReason;
    private float penalty;
    private String penaltyReason;
}
