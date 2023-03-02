package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MainTableDto;
import com.example.springsecurity2023.modal.RenameDto;
import com.example.springsecurity2023.service.interfaces.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/table")
@CrossOrigin

public class TableController {
    //THIS SITE IS ONLY ALLOWED TO COMPANY OWNERS

    //AVOIDING FROM @Autowired
    //USING LOGGER
    //ADDING COMMITS TO ALL METHODS
    //AVOIDING FROM AN OBJECT AND SPECIFYING A CLASS NAME (ResponseEntity<?> to ResponseEntity<FinalResponse>)
    //ADDING SPECIFIC PATH NAME BY WORDS TO EACH METHOD('/add', '/get', '/delete')


    private final TableService tableService;

    public TableController(TableService tableService){
        this.tableService = tableService;
    }

    private final static Logger LOGGER = Logger.getLogger(TableController.class.getName());

    //CREATING A NEW BIG TABLE
    @PostMapping("/add")
    public ResponseEntity<FinalResponse> createTable(@CurrentUser User user, @RequestBody MainTableDto dto){
        LOGGER.info("createTable(user="+user+", dto="+dto+")");
        FinalResponse finalResponse = tableService.createTable(dto, user);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("createTable(...)="+response);
        return response;
    }

    //EDITING A BIG TABLE BY OWNER TOKEN
    @PutMapping("/edit")
    public ResponseEntity<FinalResponse> editTable(@CurrentUser User user, @RequestBody RenameDto dto){
        LOGGER.info("editTable(user="+user+", dto="+dto+")");
        FinalResponse finalResponse = tableService.editName(dto, user);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("editTable(...)="+response);
        return response;
    }

    //DELETING A BIG TABLE BY OWNER ID
    @DeleteMapping("/delete")
    public ResponseEntity<FinalResponse> deleteTable(@CurrentUser User user, @RequestBody MainTableDto dto){
        LOGGER.info("deleteTable(user="+user+", dto="+dto+")");
        FinalResponse finalResponse = tableService.deleteTable(user, dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("deleteTable(...)="+response);
        return response;
    }

    //GETTING A BIG TABLE BY OWNER TOKEN
    @GetMapping("/getAll")
    public ResponseEntity<List<MainTable>> getListOfTablesByCompanyId(@CurrentUser User user){
        LOGGER.info("getListOfTablesByCompanyId(user="+user+")");
        List<MainTable> list = tableService.getListByCompanyId(user);
        ResponseEntity<List<MainTable>> response = ResponseEntity.ok(list);
        LOGGER.info("getListOfTablesByCompanyId(...)="+response);
        return response;
    }

}
