package com.example.springsecurity2023.modal;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.template.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MonthlyTableDto {

    private String month;

    private String previousMonth;

    private String employeeEmail;

    private UUID mainTableId;
}
