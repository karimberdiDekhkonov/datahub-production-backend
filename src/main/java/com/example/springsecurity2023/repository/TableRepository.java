package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.Company;
import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.User;
import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TableRepository extends JpaRepository<MainTable, UUID> {
    Boolean existsByNameAndCompanyId_OwnerId(String name, User user);

    Optional<MainTable> findByCompanyId_OwnerIdAndName(User user, String name);
    List<MainTable> findByCompanyId_OwnerId(User user);
}
