package com.example.springsecurity2023.service.interfaces;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MonthlyTableDto;

import java.util.List;
import java.util.UUID;

public interface MonthlyTableService {
    FinalResponse createMonthlyTable(MonthlyTableDto dto);

    MonthlyTable getByEmailAndMonth(String email, String month);

    List<MonthlyTable> getByTableIdAndMonth(UUID id, String month);

    FinalResponse monthlyUpdate(MonthlyTableDto dto);
}
