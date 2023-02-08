package com.example.springsecurity2023.service.interfaces;

import com.example.springsecurity2023.entity.Schedule;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.ScheduleDto;

public interface ScheduleService {
    Schedule getEmployeeSchedule(User user);

    FinalResponse resetSchedule(ScheduleDto dto);

    Schedule getByOwner(String email);
}
