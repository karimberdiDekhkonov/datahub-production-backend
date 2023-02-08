package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.Company;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.CompanyDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.repository.CompanyRepository;
import com.example.springsecurity2023.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public FinalResponse createCompany(CompanyDto companyDto, User user) {
       try {
           Optional<Company> optional = companyRepository.findByOwnerId(user);
           if (optional.isEmpty()){
              companyRepository.save(
                      new Company(
                              companyDto.getName(),
                              user
                      )
              );
               return new FinalResponse("Successfully created",true);
           }
           Company company = optional.get();
           company.setName(companyDto.getName());
           companyRepository.save(company);
           return new FinalResponse("Successfully changed", true);
       }
       catch (Exception e){
           return new FinalResponse("Something went wrong", false);
       }
    }


    @Override
    public FinalResponse getCompany(User user) {
        Optional<Company> optional = companyRepository.findByOwnerId(user);
        if (optional.isEmpty()) return new FinalResponse("You don not have company yet", false);
        Company company = optional.get();
        return new FinalResponse(company.getName(),true);
    }

    @Override
    public FinalResponse deleteUser(User user) {
       try {
           Optional<Company> optional = companyRepository.findByOwnerId(user);
           if (optional.isEmpty()) return new FinalResponse("You don't have company yet to delete", false);
           companyRepository.delete(optional.get());
           return new FinalResponse("Deleted",true);
       }catch (Exception e){
           return new FinalResponse(e.getMessage(),false);
       }
    }
}
