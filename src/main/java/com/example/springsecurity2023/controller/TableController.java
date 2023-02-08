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

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/table")
@CrossOrigin

public class TableController {

    @Autowired
    TableService tableService;

    //done
    @PostMapping
    public ResponseEntity<?> createTable(@CurrentUser User user, @RequestBody MainTableDto dto){
        FinalResponse finalResponse = tableService.createTable(dto, user);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    //done
    @PutMapping
    public ResponseEntity<?> editTable(@CurrentUser User user, @RequestBody RenameDto dto){
        FinalResponse finalResponse = tableService.editName(dto, user);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    //done
    @DeleteMapping()
    public ResponseEntity<?> deleteTable(@CurrentUser User user, @RequestBody MainTableDto dto){
        FinalResponse finalResponse = tableService.deleteTable(user, dto);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

//    @GetMapping("/one")
//    public ResponseEntity<?> getTable(@CurrentUser User user){
//        MainTable mainTable = tableService.getTableName(user);
//        return ResponseEntity.ok(mainTable);
//    }

    //done
    @GetMapping("/getAll")
    public ResponseEntity<?> getListByCompanyId(@CurrentUser User user){
        List<MainTable> list = tableService.getListByCompanyId(user);
        return ResponseEntity.ok(list);
    }

}
