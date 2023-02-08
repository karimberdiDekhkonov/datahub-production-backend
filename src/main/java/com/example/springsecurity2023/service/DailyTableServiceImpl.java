package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.DailyTable;
import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.DailyTableDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.PenaltyOrTipDto;
import com.example.springsecurity2023.repository.DailyTableRepository;
import com.example.springsecurity2023.repository.MonthlyTableRepository;
import com.example.springsecurity2023.repository.UserRepository;
import com.example.springsecurity2023.service.interfaces.DailyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class DailyTableServiceImpl implements DailyTableService {

    @Autowired
    DailyTableRepository tableRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    MonthlyTableRepository monthlyTableRepository;

    @Override
    public FinalResponse createDailyTable(DailyTableDto dto) {
        Optional<MonthlyTable> optionalMonthlyTable = monthlyTableRepository.findMonthlyTableById(dto.getMonthlyTableId());
        if (optionalMonthlyTable.isEmpty()) return new FinalResponse("Table id is wrong", false);
        Optional<DailyTable> tableOptional = tableRepository.findByMonthlyTableAndDay(optionalMonthlyTable.get(), dto.getDay());
        MonthlyTable monthlyTable = optionalMonthlyTable.get();
        if (tableOptional.isPresent()) {
            DailyTable table = tableOptional.get();
            float temp = monthlyTable.getTotalHours() -  table.getDuration() + dto.getDuration();
            table.setWorkingHours(dto.getWorkingHours());
            table.setDuration(dto.getDuration());
            table.setConfirm(dto.isConfirm());
            tableRepository.save(table);

            monthlyTable.setTotalHours(temp);
            monthlyTable.setTotalSalary(temp * dto.getSalary());
            monthlyTableRepository.save(monthlyTable);
            return new FinalResponse("Successfully changed", true);
        }
        DailyTable table = new DailyTable(optionalMonthlyTable.get(), dto.getWorkingHours(), dto.getDuration(), 0, "0", 0, "0", dto.getDay(), dto.isConfirm());
        tableRepository.save(table);
        float temp = monthlyTable.getTotalHours() + dto.getDuration();

        monthlyTable.setTotalHours(temp);
        monthlyTable.setTotalSalary(temp * dto.getSalary());
        monthlyTableRepository.save(monthlyTable);
        return new FinalResponse("Successfully saved", true);
    }

    @Override
    public List<DailyTable> getByMonthlyTableId(UUID id) {
        Optional<MonthlyTable> monthlyTableById = monthlyTableRepository.findMonthlyTableById(id);
        if (monthlyTableById.isEmpty()) throw new RuntimeException();
        return tableRepository.findDailyTableByMonthlyTable(monthlyTableById.get());
    }

    @Override
    public FinalResponse setPenaltyOrTip(PenaltyOrTipDto dto) {
        Optional<DailyTable> optional = tableRepository.findById(dto.getDailyTableId());
        if (optional.isEmpty()) return new FinalResponse("Daily table is not found", true);
        DailyTable table = optional.get();
        table.setTip(dto.getTip());
        table.setTipReason(dto.getTipReason());
        table.setPenalty(dto.getPenalty());
        table.setPenaltyReason(dto.getPenaltyReason());
        Optional<MonthlyTable> tableOptional = monthlyTableRepository.findMonthlyTableById(dto.getMonthlyTableId());
        if (tableOptional.isEmpty()) return new FinalResponse("Monthly table is not found", false);
        MonthlyTable monthlyTable = tableOptional.get();
        monthlyTable.setTotalSalary(monthlyTable.getTotalSalary() + dto.getTip() - dto.getPenalty());
        tableRepository.save(table);
        monthlyTableRepository.save(monthlyTable);
        return new FinalResponse("Successfully added", true);
    }

}
