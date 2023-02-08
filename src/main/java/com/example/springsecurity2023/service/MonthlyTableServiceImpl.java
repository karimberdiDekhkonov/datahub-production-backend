package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MonthlyTableDto;
import com.example.springsecurity2023.repository.MonthlyTableRepository;
import com.example.springsecurity2023.repository.TableRepository;
import com.example.springsecurity2023.repository.UserRepository;
import com.example.springsecurity2023.service.interfaces.MonthlyTableService;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MonthlyTableServiceImpl implements MonthlyTableService {

    @Autowired
    MonthlyTableRepository tableRepository;

    @Autowired
    TableRepository mainTableRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public FinalResponse createMonthlyTable(MonthlyTableDto dto) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmployeeEmail());
        if (optionalUser.isEmpty())return new FinalResponse("User is not found", false);
        Optional<MainTable> mainTableOptional = mainTableRepository.findById(dto.getMainTableId());
        if (mainTableOptional.isEmpty()) return new FinalResponse("Main table is not found", false);
        User user = optionalUser.get();
        Optional<MonthlyTable> byEmployeeAndMonth = tableRepository.findByEmployeeAndMonth(user, dto.getMonth());
        if (byEmployeeAndMonth.isPresent()) return new FinalResponse("You already have this table", false);
        MainTable mainTable = mainTableOptional.get();
        MonthlyTable monthlyTable = new MonthlyTable(dto.getMonth(), user, mainTable);
        tableRepository.save(monthlyTable);
        return new FinalResponse("Successfully created", true);
    }

    @Override
    public MonthlyTable getByEmailAndMonth(String email, String month) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) return new MonthlyTable();
        Optional<MonthlyTable> optional = tableRepository.findByEmployeeAndMonth(userOptional.get(), month);
        if (optional.isEmpty()) return new MonthlyTable();
        return optional.get();
    }

    @Override
    public List<MonthlyTable> getByTableIdAndMonth(UUID id, String month) {
        return tableRepository.findByMainTable_IdAndMonth(id, month);
    }

    @Override
    public FinalResponse monthlyUpdate(MonthlyTableDto dto) {
      try {
          List<MonthlyTable> tableList = tableRepository.findByMainTable_IdAndMonth(dto.getMainTableId(), dto.getPreviousMonth());
          for(MonthlyTable table : tableList){
              dto.setEmployeeEmail(table.getEmployee().getEmail());
              createMonthlyTable(dto);
          }
          return new FinalResponse("Successfully created", true);
      }catch (Exception e){
          return new FinalResponse(e.getMessage(), false);

      }
    }
}
