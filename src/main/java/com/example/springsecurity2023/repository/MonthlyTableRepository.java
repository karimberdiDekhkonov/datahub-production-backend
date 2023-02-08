package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.MainTable;
import com.example.springsecurity2023.entity.MonthlyTable;
import com.example.springsecurity2023.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonthlyTableRepository extends JpaRepository<MonthlyTable, UUID> {
    Optional<MonthlyTable> findByEmployeeAndMonth(User user, String month);
    Optional<MonthlyTable> findMonthlyTableById(UUID id);
    List<MonthlyTable> findByMainTable_IdAndMonth(UUID id, String month);
}
