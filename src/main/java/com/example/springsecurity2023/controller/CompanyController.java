package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.Company;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.CompanyDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/company")
@CrossOrigin
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> addCompany(@CurrentUser User user, @RequestBody CompanyDto companyDto){
        FinalResponse finalResponse = companyService.createCompany(companyDto, user);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    @GetMapping
    public ResponseEntity<?> getCompany(@CurrentUser User user){
        FinalResponse finalResponse = companyService.getCompany(user);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany(@CurrentUser User user){
        FinalResponse finalResponse = companyService.deleteUser(user);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }
}
