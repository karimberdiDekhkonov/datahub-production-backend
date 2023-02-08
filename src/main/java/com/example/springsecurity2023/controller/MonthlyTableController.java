package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MonthlyTableDto;
import com.example.springsecurity2023.service.interfaces.MonthlyTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/monthly")
@CrossOrigin

public class MonthlyTableController {

    @Autowired
    MonthlyTableService service;

    //done
    @PostMapping
    public ResponseEntity<?> createMonthlyTable(@RequestBody MonthlyTableDto dto){
        FinalResponse response = service.createMonthlyTable(dto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }


    //done //for employee
    @GetMapping("/getByEmailAndMonth")
    public ResponseEntity<?> getByEmailAndMonth(@RequestParam String email,@RequestParam String month){
        MonthlyTable response = service.getByEmailAndMonth(email, month);
        return ResponseEntity.ok(response);
    }

    //done //for owner
    @GetMapping("/getByTableIdAndMonth")
    public ResponseEntity<?> getByTableIdAndMonth(@RequestParam UUID id,@RequestParam String month){
        List<MonthlyTable> response = service.getByTableIdAndMonth(id, month);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/monthlyUpdate")
    public ResponseEntity<?> monthlyUpdate(@RequestBody MonthlyTableDto dto){
        FinalResponse response = service.monthlyUpdate(dto);
        return ResponseEntity.ok(response);
    }


}

