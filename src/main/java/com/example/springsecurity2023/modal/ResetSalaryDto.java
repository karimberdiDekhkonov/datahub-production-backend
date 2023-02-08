package com.example.springsecurity2023.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResetSalaryDto {
    private String email;
    private String salary;
}
