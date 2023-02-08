package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.*;
import com.example.springsecurity2023.entity.enums.NewRole;
import com.example.springsecurity2023.modal.*;
import com.example.springsecurity2023.repository.*;
import com.example.springsecurity2023.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    JWTService jwtService;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    MonthlyTableRepository monthlyTableRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    public static String message;

    public ApiResponse registerUser(RegisterDto registerDto) {
        Optional<User> optionalUser = userRepository.findByEmail(registerDto.getEmail());
        if (optionalUser.isPresent()){
            return new ApiResponse(false,"You have already registered, please verify your email by code");
        }
        int code = new Random().nextInt(99999);
        message = "Hey " + registerDto.getFirstname() + "!" +
                "\r\n\nTo complete the sign up, enter the verification code below: " +
                "\n\n\nVerification code: " + String.valueOf(code).substring(0,5);
        Boolean sent = sendMail(registerDto.getEmail(), message);
        if (!sent) return new ApiResponse(false,"We can not send a security code");
        User user = new User(registerDto.getFirstname(), registerDto.getEmail(), encoder.encode(registerDto.getPassword()), NewRole.USER, code);
        userRepository.save(user);
        return new ApiResponse(true, "Successfully registered !");
    }

    public ApiResponse login(LoginDto loginDto) {
      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          loginDto.getEmail(),
                          loginDto.getPassword()
                  )
          );

          var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
          var jwtToken = jwtService.generateToken(user);
          return ApiResponse.builder()
                  .token(jwtToken)
                  .build();
      }
      catch (Exception e){
          return new ApiResponse(true,"Email or password is wrong");
      }
    }

    public FinalResponse verifyEmail(String email, int emailCode) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            if (emailCode == user.getSecurityCode()){
                user.setEnabled(true);
                user.setSecurityCode(0);
                userRepository.save(user);
                String token = jwtService.generateToken(user);
                return new FinalResponse(token,true);
            }
            return new FinalResponse("Code is wrong",false);
        }
        return new FinalResponse("Email is wrong", false);
    }

    public Boolean sendMail(String sendingEmail,String message){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("Verification Code");
            mailMessage.setFrom("DataHub.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public FinalResponse deleteUser(User user) {
       try {
           userRepository.deleteById(user.getId());
           return new FinalResponse("Successfully deleted", true);
       }catch (Exception e){
           return new FinalResponse(e.getMessage(), false);
       }
    }

    public FinalResponse resend(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) return new FinalResponse("You did not registered yet", false);
        User user = optional.get();
        int code = new Random().nextInt(99999);
        message = "Hey " + user.getFirstname() + "!" +
                "\r\n\nTo complete the sign up, enter the verification code below: " +
                "\n\n\nVerification code: " + String.valueOf(code).substring(0,5);
        Boolean sent = sendMail(user.getEmail(), message);
        if (!sent) return new FinalResponse("We can not send a security code",false);
        user.setSecurityCode(code);
        userRepository.save(user);
        return new FinalResponse( "Successfully sent !", true);
    }

    public FinalResponse resetName(User user, CompanyDto loginDto) {
        try {
            user.setFirstname(loginDto.getName());
            userRepository.save(user);
            return new FinalResponse("Successfully changed !",true);
        }catch (Exception e){
            return new FinalResponse(e.getMessage(),false);
        }
    }

    public FinalResponse resetPassword(User user, CompanyDto companyDto) {
        try {
            user.setPassword(encoder.encode(companyDto.getName()));
            userRepository.save(user);
            return new FinalResponse("Successfully changed !",true);
        }catch (Exception e){
            return new FinalResponse(e.getMessage(),false);
        }
    }

    public FinalResponse registerByOwner(User owner, RegisterByOwnerDto registerDto) {
        Optional<Company> optional = companyRepository.findByOwnerId(owner);
        if (optional.isEmpty()) return new FinalResponse("Please first change company name!", false);
        Company company = optional.get();
        Optional<User> optionalUser = userRepository.findByEmail(registerDto.getEmail());
        if (optionalUser.isPresent()) return new FinalResponse("Please enter new email address hi is already joined to another company", false);
            message = "Hey there!" +
                    "\r\n\nYou have been registered to DataHub.com by your company owner: " +
                    "\nYour name: 'I'm your name' ;" +
                    "\nYour email: " + registerDto.getEmail() + ";" +
                    "\nYour password: 'password';" +
                    "\n\n\nYou can change your data any time you want. Welcome to DataHub.com";
            Boolean sent = sendMail(registerDto.getEmail(), message);
            if (!sent) return new FinalResponse("It seems like employee email is wrong", false);
            User user = new User();
            user.setFirstname("I am your first name");
            user.setEmail(registerDto.getEmail());
            user.setPassword(encoder.encode("password"));
            user.setRole(NewRole.USER);
            user.setEnabled(true);
            user.setSalary(registerDto.getSalary());
            user.setJoinedCompanyId(company);
            userRepository.save(user);


            Schedule schedule = new Schedule(user, registerDto.getMonday(), registerDto.getTuesday(), registerDto.getWednesday(), registerDto.getThursday(), registerDto.getFriday(), registerDto.getSaturday(), registerDto.getSunday());
            scheduleRepository.save(schedule);

            Optional<MainTable> mainTableOptional = tableRepository.findById(registerDto.getTableId());
            if (mainTableOptional.isEmpty()) return new FinalResponse("Main table is not found", false);
            Optional<MonthlyTable> byEmployeeAndMonth = monthlyTableRepository.findByEmployeeAndMonth(user, registerDto.getMonth());
            if (byEmployeeAndMonth.isPresent()) return new FinalResponse("You already have this table", false);
            MainTable mainTable = mainTableOptional.get();
            MonthlyTable monthlyTable = new MonthlyTable(registerDto.getMonth(), user, mainTable);
            monthlyTableRepository.save(monthlyTable);

        return new FinalResponse("Invitation link is sent to his email address.  \nWarning! Let him to check his banned emails list", true);
    }

    public FinalResponse resetSalary(ResetSalaryDto dto) {
        Optional<User> optional = userRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()) return new FinalResponse("User is not found", false);
        User user = optional.get();
        user.setSalary(dto.getSalary());
        userRepository.save(user);
        return new FinalResponse("Successfully changed", true);
    }

    public User getEmployee(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        return optional.orElseGet(User::new);
    }

    public List<User> getEmployees(User user) {
        Optional<Company> companyOptional = companyRepository.findByOwnerId(user);
        if (companyOptional.isEmpty()) return new ArrayList<>();
        List<User> list = userRepository.findByJoinedCompanyId(companyOptional.get());
        return list;
    }
}
