//package com.example.springsecurity2023.schedule;
//
//import com.example.springsecurity2023.controller.DailyTableController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.UUID;
//
//@Component
//public class Scheduler {
//    @Autowired
//    DailyTableController dailyTableController;
//
//    @Scheduled(fixedDelay = 1000)
//    public void tester(){
//        LocalDate date = LocalDate.now();
////        private UUID monthlyTableId;
////        private String workingHours;
////        private String duration;
////        private String tip;
////        private String tipReason;
////        private String penalty;
////        private String penaltyReason;
////        private int day;
////        private boolean confirm;
////        dailyTableController.createDailyRow();
//        System.out.println(String.valueOf(date.getDayOfWeek()).toLowerCase());
//    }
//}
