package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.Schedule;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.ScheduleDto;
import com.example.springsecurity2023.service.interfaces.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/api/schedule")
@CrossOrigin
public class ScheduleController {

    //AVOIDING FROM @Autowired
    //USING LOGGER
    //ADDING COMMITS TO ALL METHODS
    //AVOIDING FROM AN OBJECT AND SPECIFYING A CLASS NAME (ResponseEntity<?> to ResponseEntity<FinalResponse>)

    private final ScheduleService service;

    public ScheduleController(ScheduleService service){
        this.service = service;
    }

    private final static Logger LOGGER = Logger.getLogger(ScheduleController.class.getName());

    //GETTING EMPLOYEE'S SCHEDULE BY THEM
    @GetMapping
    public ResponseEntity<Schedule> getEmployeeSchedule(@CurrentUser User user){
        LOGGER.info("getEmployeeSchedule("+user+")");
        Schedule schedule = service.getEmployeeSchedule(user);
        ResponseEntity<Schedule> response = ResponseEntity.ok(schedule);
        LOGGER.info("getEmployeeSchedule(...)="+response);
        return response;
    }

    //GETTING EMPLOYEE'S SCHEDULE BY COMPANY OWNERS
    @GetMapping("/getByOwner")
    public ResponseEntity<Schedule> getByOwner(@RequestParam String email){
        LOGGER.info("getByOwner(email="+email+")");
        Schedule schedule = service.getByOwner(email);
        ResponseEntity<Schedule> response = ResponseEntity.ok(schedule);
        LOGGER.info("getByOwner(...)="+response);
        return response;
    }

    //CREATING A NEW SCHEDULE OR EDITING IF THAT SCHEDULE IS ALREADY EXIST BY COMPANY OWNERS (THIS PATH IS ONLY ALLOWED FOR OWNERS)
    @PutMapping
    public ResponseEntity<FinalResponse> resetSchedule(@RequestBody ScheduleDto dto){
        LOGGER.info("resetSchedule("+dto+")");
        FinalResponse finalResponse = service.resetSchedule(dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("resetSchedule(...)="+response);
        return response;
    }
}
