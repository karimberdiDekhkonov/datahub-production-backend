package com.example.springsecurity2023.service.interfaces;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.entity.anotations.CurrentUser;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.MainTableDto;
import com.example.springsecurity2023.modal.RenameDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TableService {

    FinalResponse createTable(MainTableDto dto, User user);

    FinalResponse editName(RenameDto dto, User user);

    FinalResponse deleteTable(User user, MainTableDto dto);
//
//    MainTable getTableName(User user);

    List<MainTable> getListByCompanyId(User user);
}
