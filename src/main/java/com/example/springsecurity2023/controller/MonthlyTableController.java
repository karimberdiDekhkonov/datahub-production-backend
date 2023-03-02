package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MonthlyTableDto;
import com.example.springsecurity2023.service.interfaces.MonthlyTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/monthly")
@CrossOrigin

public class MonthlyTableController {

    //AVOIDING FROM @Autowired
    //USING LOGGER
    //ADDING COMMITS TO ALL METHODS
    //AVOIDING FROM AN OBJECT AND SPECIFYING A CLASS NAME (ResponseEntity<?> to ResponseEntity<FinalResponse>)

    private final MonthlyTableService service;

    public MonthlyTableController(MonthlyTableService service){
        this.service = service;
    }

    private final static Logger LOGGER = Logger.getLogger(MonthlyTableController.class.getName());


    //CREATING A NEW MONTHLY TABLE AT THE FIRST TIME BY OWNERS
    @PostMapping
    public ResponseEntity<FinalResponse> createMonthlyTable(@RequestBody MonthlyTableDto dto){
        LOGGER.info("getByEmailAndMonth("+dto+")");
        FinalResponse finalResponse = service.createMonthlyTable(dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("createMonthlyTable(...)="+response);
        return response;
    }

    //GETTING MONTHLY TABLE DATA BY EMPLOYEES
    @GetMapping("/getByEmailAndMonth")
    public ResponseEntity<MonthlyTable> getByEmailAndMonth(@RequestParam String email,@RequestParam String month){
        LOGGER.info("getByEmailAndMonth(email="+email+", month="+month+")");
        MonthlyTable monthlyTable = service.getByEmailAndMonth(email, month);
        ResponseEntity<MonthlyTable> response = ResponseEntity.ok(monthlyTable);
        LOGGER.info("getByEmailAndMonth(...)="+response);
        return response;
    }

    //GETTING MONTHLY TABLES DATA AS A LIST BY OWNERS
    @GetMapping("/getByTableIdAndMonth")
    public ResponseEntity<List<MonthlyTable>> getByTableIdAndMonth(@RequestParam UUID id,@RequestParam String month){
        LOGGER.info("getByTableIdAndMonth(id="+id+", month="+month+")");
        List<MonthlyTable> list = service.getByTableIdAndMonth(id, month);
        ResponseEntity<List<MonthlyTable>> response = ResponseEntity.ok(list);
        LOGGER.info("getByTableIdAndMonth(...)="+response);
        return response;
    }

    //CREATING A NEW MONTHLY TABLE AND THE REQUEST WILL COME AUTOMATICALLY IN EVERY MONTH
    @PostMapping("/monthlyUpdate")
    public ResponseEntity<FinalResponse> monthlyUpdate(@RequestBody MonthlyTableDto dto){
        LOGGER.info("monthlyUpdate("+dto+")");
        FinalResponse finalResponse = service.monthlyUpdate(dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.ok(finalResponse);
        LOGGER.info("monthlyUpdate(...)="+response);
        return response;
    }


}

