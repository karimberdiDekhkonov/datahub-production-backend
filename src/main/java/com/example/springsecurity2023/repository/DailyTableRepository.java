package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.DailyTable;
import com.example.springsecurity2023.entity.MonthlyTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DailyTableRepository extends JpaRepository<DailyTable, UUID> {
    Optional<DailyTable> findByMonthlyTableAndDay(MonthlyTable monthlyTable, int day);

    List<DailyTable> findDailyTableByMonthlyTable(MonthlyTable table);
}
