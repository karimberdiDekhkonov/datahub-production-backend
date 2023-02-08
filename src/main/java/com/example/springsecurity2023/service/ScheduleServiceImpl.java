package com.example.springsecurity2023.service;

import com.example.springsecurity2023.entity.Schedule;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.ScheduleDto;
import com.example.springsecurity2023.repository.ScheduleRepository;
import com.example.springsecurity2023.repository.UserRepository;
import com.example.springsecurity2023.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Schedule getEmployeeSchedule(User user) {
        Optional<Schedule> optional = scheduleRepository.findScheduleByEmployeeId(user);
        if(optional.isEmpty()) return new Schedule();
        return optional.get();
    }

    @Override
    public FinalResponse resetSchedule(ScheduleDto dto) {
        Optional<User> optional = userRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()) return new FinalResponse("User is not found", false);
        Optional<Schedule> scheduleOptional = scheduleRepository.findScheduleByEmployeeId(optional.get());
        if (scheduleOptional.isEmpty()) return new FinalResponse("Schedule is not found", false);
        Schedule schedule = scheduleOptional.get();
        schedule.setMonday(dto.getMonday());
        schedule.setTuesday(dto.getTuesday());
        schedule.setWednesday(dto.getWednesday());
        schedule.setThursday(dto.getThursday());
        schedule.setFriday(dto.getFriday());
        schedule.setSaturday(dto.getSaturday());
        schedule.setSunday(dto.getSunday());
        scheduleRepository.save(schedule);
        return new FinalResponse("Successfully changed", true);
    }

    @Override
    public Schedule getByOwner(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) return new Schedule();
        Optional<Schedule> optional = scheduleRepository.findScheduleByEmployeeId(userOptional.get());
        if (optional.isEmpty()) return new Schedule();
        return optional.get();
    }
}
