package com.example.springsecurity2023.controller;

import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.*;
import com.example.springsecurity2023.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse>  methodRegister(@RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> methodRegister(@RequestBody LoginDto loginDto){
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email, @RequestParam int emailCode){
        FinalResponse apiResponse = authService.verifyEmail(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/resend")
    public HttpEntity<?> resendCode(@RequestParam String email){
        FinalResponse apiResponse = authService.resend(email);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getUser(@CurrentUser User user){
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@CurrentUser User user){
        FinalResponse finalResponse = authService.deleteUser(user);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    @PutMapping("/resetName")
    public ResponseEntity<?> resetName(@CurrentUser User user, @RequestBody CompanyDto companyDto){
        FinalResponse finalResponse = authService.resetName(user, companyDto);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@CurrentUser User user, @RequestBody CompanyDto companyDto){
        FinalResponse finalResponse = authService.resetPassword(user, companyDto);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    //done
    @PostMapping("/registerByOwner")
    public ResponseEntity<?>  registerByOwner(@CurrentUser User user, @RequestBody RegisterByOwnerDto registerDto){
        FinalResponse finalResponse = authService.registerByOwner(user, registerDto);
        return ResponseEntity.status(finalResponse.isSuccess()?200:409).body(finalResponse);
    }

    @PutMapping("/resetSalary")
    public ResponseEntity<?> resetSalary(@RequestBody ResetSalaryDto dto){
        FinalResponse response = authService.resetSalary(dto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<?> getEmployee(@RequestParam String email){
        User user = authService.getEmployee(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getEmployees")
    public ResponseEntity<?> getEmployees(@CurrentUser User user){
        List<User> list = authService.getEmployees(user);
        return ResponseEntity.ok(list);
    }
}
