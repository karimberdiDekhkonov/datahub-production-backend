package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.Company;
import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MainTableDto;
import com.example.springsecurity2023.modal.RenameDto;
import com.example.springsecurity2023.repository.CompanyRepository;
import com.example.springsecurity2023.repository.TableRepository;
import com.example.springsecurity2023.service.interfaces.TableService;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service

public class TableServiceImpl implements TableService {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public FinalResponse createTable(MainTableDto dto, User user) {
        Optional<Company> companyOptional = companyRepository.findByOwnerId(user);
        if (companyOptional.isEmpty()) {
            Company company = new Company(
                    "I'm your company name",
                    user
            );
            companyRepository.save(company);

            MainTable mainTable = new MainTable(dto.getName(), company);
            tableRepository.save(mainTable);
            return new FinalResponse("Successfully created", true);
        }
        Company company = companyOptional.get();
        Boolean exists = tableRepository.existsByNameAndCompanyId_OwnerId(dto.getName(), user);
        if (exists) return new FinalResponse("You already have this table", false);

        MainTable table = new MainTable(dto.getName(), company);
        tableRepository.save(table);
        return new FinalResponse("Successfully created", true);
    }

    @Override
    public FinalResponse editName(RenameDto dto, User user) {
        Optional<MainTable> optional = tableRepository.findByCompanyId_OwnerIdAndName(user, dto.getName());
        if (optional.isEmpty()) return new FinalResponse("Wrong id", false);
        Boolean exists = tableRepository.existsByNameAndCompanyId_OwnerId(dto.getNewName(), user);
        if (exists) return new FinalResponse("You already have a table with this name", false);
        MainTable table = optional.get();
        table.setName(dto.getNewName());
        tableRepository.save(table);
        return new FinalResponse("Successfully changed", true);
    }

    @Override
    public FinalResponse deleteTable( User user, MainTableDto dto) {
        Optional<MainTable> optional = tableRepository.findByCompanyId_OwnerIdAndName(user, dto.getName());
        if (optional.isEmpty()) return new FinalResponse("Wrong table id", false);
        tableRepository.delete(optional.get());
        return new FinalResponse("Successfully deleted", true);
    }
//
//    @Override
//    public MainTable getTableName(User user) {
//        return tableRepository.findByCompanyId_OwnerId(user);
//    }

    @Override
    public List<MainTable> getListByCompanyId(User user) {
        Optional<Company> companyOptional = companyRepository.findByOwnerId(user);
        if (companyOptional.isEmpty()) return new ArrayList<>();
        return tableRepository.findByCompanyId_OwnerId(user);
    }
}
