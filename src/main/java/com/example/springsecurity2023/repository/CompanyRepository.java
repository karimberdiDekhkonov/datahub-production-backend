package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.Company;
import com.example.springsecurity2023.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByOwnerId(User ownerId);

}
