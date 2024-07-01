package com.s.bigevent.service.impl;

import com.s.bigevent.domain.dto.HistoryDTO;
import com.s.bigevent.domain.vo.HistoryVO;
import com.s.bigevent.mapper.HistoryMapper;
import com.s.bigevent.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<HistoryVO> getHistoriesByUserId(Integer userId) {
        return historyMapper.getHistoriesByUserId(userId);
    }

    @Override
    public Integer findHistoryByIds(HistoryDTO historyDTO) {
        return historyMapper.getHistoryByIds(historyDTO) == null ? -1 : Integer.parseInt(historyMapper.getHistoryByIds(historyDTO));
    }

    @Override
    public void updateHistory(HistoryDTO historyDTO) {
        historyMapper.updateHistory(historyDTO);
    }

    @Override
    public void addHistory(HistoryDTO historyDTO) {
        historyMapper.addHistory(historyDTO);
    }
}
