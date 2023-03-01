package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.*;
import com.example.springsecurity2023.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    //AVOIDING @Autowired
    //USING LOGGER
    //ADDING COMMITS TO ALL METHODS
    //AVOIDING AN OBJECT AND SPECIFYING A CLASS NAME (ResponseEntity<?> to ResponseEntity<FinalResponse>)
    private final AuthService authService;

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //REGISTER A NEW USER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse>  methodRegister(@RequestBody RegisterDto registerDto){
        LOGGER.info("methodRegister = (" + registerDto + ")");
        ApiResponse apiResponse = authService.registerUser(registerDto);
        ResponseEntity<ApiResponse> response = ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
        LOGGER.info("methodRegister(...) = " + response);
        return response;
    }

    //LOGIN USER
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginDto loginDto){
        LOGGER.info("loginUser (" + loginDto + ")");
        ApiResponse apiResponse = authService.login(loginDto);
        ResponseEntity<ApiResponse> response= ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
        LOGGER.info("loginUser(...)" + response);
        return response;
    }

    //EMAIL VERIFICATION PROCESS AND SETTING USER ENABLE=TRUE
    @PutMapping("/verifyEmail")
    public HttpEntity<FinalResponse> verifyEmail(@RequestParam String email, @RequestParam int emailCode){
        LOGGER.info("verifyEmail(email = " + email + ", code = " + emailCode + ")");
        FinalResponse apiResponse = authService.verifyEmail(email, emailCode);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
        LOGGER.info("verifyEmail(...) = " + response);
        return response;
    }

    //RESENDING AND RESETTING VERIFICATION CODE TO USER
    @PutMapping("/resend")
    public HttpEntity<FinalResponse> resendCode(@RequestParam String email){
        LOGGER.info("resendCode(email = "+email+")");
        FinalResponse apiResponse = authService.resend(email);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
        LOGGER.info("resend(...) = "+response);
        return response;
    }

    //IT RETURNS ALL OF USER INFORMATION
    @GetMapping()
    public ResponseEntity<User> getUser(@CurrentUser User user){
        LOGGER.info("getUser("+ user +")");
        LOGGER.info("getUser(...) = " +user);
        return ResponseEntity.ok(user);
    }

    //DELETE USER BY TOKEN
    @DeleteMapping
    public ResponseEntity<FinalResponse> deleteUser(@CurrentUser User user){
        LOGGER.info("deleteUser("+user+")");
        FinalResponse finalResponse = authService.deleteUser(user);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("deleteUser(...) = " + response);
        return response;
    }

    //RESETTING USER'S NAME
    @PutMapping("/resetName")
    public ResponseEntity<FinalResponse> resetName(@CurrentUser User user, @RequestBody CompanyDto companyDto){
        LOGGER.info("resetName(user = "+user+", companyDto = "+companyDto);
        FinalResponse finalResponse = authService.resetName(user, companyDto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("resetName(...) = "+response);
        return response;
    }

    //RESETTING USER'S PASSWORD
    @PutMapping("/resetPassword")
    public ResponseEntity<FinalResponse> resetPassword(@CurrentUser User user, @RequestBody CompanyDto companyDto){
        LOGGER.info("resetPassword(user = "+user+", dto = "+ companyDto);
        FinalResponse finalResponse = authService.resetPassword(user, companyDto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("resetPassword(...) = "+response);
        return response;
    }

    //REGISTERING A NEW USER BY HIS/HER COMPANY OWNER
    @PostMapping("/registerByOwner")
    public ResponseEntity<FinalResponse>  registerByOwner(@CurrentUser User user, @RequestBody RegisterByOwnerDto registerDto){
        LOGGER.info("registerByOwner(user = "+user+",dto = "+registerDto);
        FinalResponse finalResponse = authService.registerByOwner(user, registerDto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("registerByOwner(...)="+response);
        return response;
    }

    //RESETTING USER'S HOURLY SALARY
    @PutMapping("/resetSalary")
    public ResponseEntity<FinalResponse> resetSalary(@RequestBody ResetSalaryDto dto){
        LOGGER.info("resetSalary("+dto+")");
        FinalResponse finalResponse = authService.resetSalary(dto);
        ResponseEntity<FinalResponse> response = ResponseEntity.status(finalResponse.isSuccess() ? 200 : 409).body(finalResponse);
        LOGGER.info("resetSalary(...)="+response);
        return response;
    }

    //GET USER BY EMAIL FOR CALCULATING HIS/HER MONTHLY SALARY
    @GetMapping("/getEmployee")
    public ResponseEntity<User> getEmployee(@RequestParam String email){
        LOGGER.info("getEmployee(email = "+email);
        User user = authService.getEmployee(email);
        ResponseEntity<User> response = ResponseEntity.ok(user);
        LOGGER.info("getEmployee(...) = "+response);
        return response;
    }

    //GET USER'S LIST THEIR COMPANY OWNER
    @GetMapping("/getEmployees")
    public ResponseEntity<List<User>> getEmployees(@CurrentUser User user){
        LOGGER.info("getEmployees(" + user + ")");
        List<User> list = authService.getEmployees(user);
        ResponseEntity<List<User>> response = ResponseEntity.ok(list);
        LOGGER.info("getEmployees(...)="+response);
        return response;
    }
}
