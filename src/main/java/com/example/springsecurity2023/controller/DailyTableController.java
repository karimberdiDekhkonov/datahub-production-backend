package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.DailyTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.DailyTableDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.PenaltyOrTipDto;
import com.example.springsecurity2023.service.interfaces.DailyTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/daily")
@RequiredArgsConstructor
@CrossOrigin

public class DailyTableController {

    @Autowired
    DailyTableService service;


    //done
    @PostMapping
    public ResponseEntity<?> createDailyRow(@RequestBody DailyTableDto dto) {
        FinalResponse finalResponse = service.createDailyTable(dto);
        return ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
    }

    //done
    @GetMapping
    public ResponseEntity<?> getDailyRow(@RequestParam UUID id){
        List<DailyTable> list = service.getByMonthlyTableId(id);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/penaltyOrTip")
    public ResponseEntity<?> setPenaltyOrTip(@RequestBody PenaltyOrTipDto dto) {
        FinalResponse finalResponse = service.setPenaltyOrTip(dto);
        return ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
    }

}
