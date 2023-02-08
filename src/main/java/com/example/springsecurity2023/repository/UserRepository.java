package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.Company;
import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional <User> findByEmail(String email);

    List<User> findByJoinedCompanyId(Company company);
}
