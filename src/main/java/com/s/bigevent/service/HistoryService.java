package com.s.bigevent.service;

import com.s.bigevent.domain.dto.HistoryDTO;
import com.s.bigevent.domain.vo.HistoryVO;

import java.util.List;

public interface HistoryService {

    List<HistoryVO> getHistoriesByUserId(Integer userId);

    Integer findHistoryByIds(HistoryDTO historyDTO);

    void updateHistory(HistoryDTO historyDTO);

    void addHistory(HistoryDTO historyDTO);
}
