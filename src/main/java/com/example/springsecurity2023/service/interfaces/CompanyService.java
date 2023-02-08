package com.example.springsecurity2023.service.interfaces;

import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.CompanyDto;
import com.example.springsecurity2023.modal.FinalResponse;

public interface CompanyService {
    FinalResponse createCompany(CompanyDto companyDto, User user);

    FinalResponse getCompany(User user);

    FinalResponse deleteUser(User user);
}
