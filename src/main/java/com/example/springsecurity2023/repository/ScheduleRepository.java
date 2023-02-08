package com.example.springsecurity2023.repository;

import com.example.springsecurity2023.entity.Schedule;
import com.example.springsecurity2023.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findScheduleByEmployeeId(User user);
}
