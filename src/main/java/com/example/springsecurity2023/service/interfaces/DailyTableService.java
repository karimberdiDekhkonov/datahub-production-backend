package com.example.springsecurity2023.service.interfaces;

import com.example.springsecurity2023.entity.DailyTable;
import com.example.springsecurity2023.entity.User;
import com.example.springsecurity2023.modal.DailyTableDto;
import com.example.springsecurity2023.modal.FinalResponse;
import com.example.springsecurity2023.modal.PenaltyOrTipDto;

import java.util.List;
import java.util.UUID;

public interface DailyTableService {

    FinalResponse createDailyTable(DailyTableDto dto);

    List<DailyTable> getByMonthlyTableId(UUID id);

    FinalResponse setPenaltyOrTip(PenaltyOrTipDto dto);
}
