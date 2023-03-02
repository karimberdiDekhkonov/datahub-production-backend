package com.example.springsecurity2023.controller;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.CompanyDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.service.interfaces.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/api/company")
@CrossOrigin
public class CompanyController {

    //AVOIDING FROM @Autowired
    //USING LOGGER
    //ADDING COMMITS TO ALL METHODS
    //AVOIDING FROM AN OBJECT AND SPECIFYING A CLASS NAME (ResponseEntity<?> to ResponseEntity<FinalResponse>)
    //ADDING SPECIFIC PATH NAME BY WORDS TO EACH METHOD('/add', '/get', '/delete')
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    private static final Logger LOGGER = Logger.getLogger(CompanyController.class.getName());

    //ADDING NEW COMPANY
    @PostMapping("/add")
    public ResponseEntity<FinalResponse> addCompany(@CurrentUser User user, @RequestBody CompanyDto companyDto){
        LOGGER.info("addCompany("+user+companyDto+")");
        FinalResponse finalResponse = companyService.createCompany(companyDto, user);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("addCompany(...)="+response);
        return response;
    }

    //GETTING COMPANY DATA BY USER TOKEN
    @GetMapping("/get")
    public ResponseEntity<FinalResponse> getCompany(@CurrentUser User user){
        LOGGER.info("getCompany("+user+")");
        FinalResponse finalResponse = companyService.getCompany(user);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("getCompany(...)="+response);
        return response;
    }

    //DELETING COMPANY BY USER TOKEN
    @DeleteMapping("/delete")
    public ResponseEntity<FinalResponse> deleteCompany(@CurrentUser User user){
        LOGGER.info("deleteCompany("+user+")");
        FinalResponse finalResponse = companyService.deleteUser(user);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("deleteCompany(...)="+response);
        return response;
    }
}
