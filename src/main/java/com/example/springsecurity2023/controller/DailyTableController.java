package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.DailyTable;
import com.example.springsecurity2023.modal.DailyTableDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.PenaltyOrTipDto;
import com.example.springsecurity2023.service.interfaces.DailyTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/daily")
@CrossOrigin

public class DailyTableController {

    //AVOIDING FROM @Autowired
    //USING LOGGER
    //ADDING COMMITS TO ALL METHODS
    //AVOIDING FROM AN OBJECT AND SPECIFYING A CLASS NAME (ResponseEntity<?> to ResponseEntity<FinalResponse>)
    private final DailyTableService service;

    public DailyTableController(DailyTableService service){
        this.service = service;
    }

    private final static Logger LOGGER = Logger.getLogger(DailyTableController.class.getName());

    //CREATING DAILY ROW AND IT INCLUDES HOURS, DURATION ...
    @PostMapping
    public ResponseEntity<FinalResponse> createDailyRow(@RequestBody DailyTableDto dto) {
        LOGGER.info("createDailyTable("+dto+")");
        FinalResponse finalResponse = service.createDailyTable(dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("createDailyTable(...)="+response);
        return response;
    }

    //GETTING DAILY ROWS FOR TABLE BY ID OF PARENT TABLE
    @GetMapping
    public ResponseEntity<List<DailyTable>> getDailyRow(@RequestParam UUID id){
        LOGGER.info("getDailyRow("+id+")");
        List<DailyTable> list = service.getByMonthlyTableId(id);
        ResponseEntity<List<DailyTable>> response = ResponseEntity.ok(list);
        LOGGER.info("getDailyRow("+response);
        return response;
    }

    //THIS PATH IS ONLY ALLOWED FOR OWNERS AND THEY CAN ADD TIPS OR PENALTIES TO THEIR EMPLOYEES
    @PutMapping("/penaltyOrTip")
    public ResponseEntity<FinalResponse> setPenaltyOrTip(@RequestBody PenaltyOrTipDto dto) {
        LOGGER.info("penaltyOrTip("+dto+")");
        FinalResponse finalResponse = service.setPenaltyOrTip(dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("penaltyOrTip("+response);
        return response;
    }

}
