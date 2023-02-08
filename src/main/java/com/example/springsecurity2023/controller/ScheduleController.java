package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.Schedule;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.ScheduleDto;
import com.example.springsecurity2023.service.interfaces.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/schedule")
@CrossOrigin
public class ScheduleController {
    @Autowired
    ScheduleService service;

    @GetMapping
    public ResponseEntity<?> getEmployeeSchedule(@CurrentUser User user){
        Schedule schedule = service.getEmployeeSchedule(user);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/getByOwner")
    public ResponseEntity<?> getByOwner(@RequestParam String email){
        Schedule schedule = service.getByOwner(email);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping
    public ResponseEntity<?> resetSchedule(@RequestBody ScheduleDto dto){
        FinalResponse response = service.resetSchedule(dto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}
