package com.example.springsecurity2023.modal;

import com.example.springsecurity2023.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterByOwnerDto {
    private String email;
    private String month;
    private UUID tableId;
    private String salary;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
}
