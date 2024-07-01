package com.s.bigevent.controller;

import com.s.bigevent.domain.Result;
import com.s.bigevent.domain.dto.HistoryDTO;
import com.s.bigevent.domain.vo.HistoryVO;
import com.s.bigevent.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/history")
@Slf4j
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    /**
     * 根据用户id获取历史浏览记录
     * @param userId
     * @return
     */
    @GetMapping
    public Result<List<HistoryVO>> getHistories (Integer userId) {
        log.info("查询历史记录：{}", userId);
        List<HistoryVO> historyVOList = historyService.getHistoriesByUserId(userId);

        return Result.success(historyVOList);
    }

    /**
     * 更新历史记录
     * @param historyDTO
     * @return
     */
    @PutMapping
    public Result updateHistory (@RequestBody HistoryDTO historyDTO) {
        log.info("更新历史记录信息：{}", historyDTO);
        //通过三种id获取历史表中对应的id信息
        Integer id = historyService.findHistoryByIds(historyDTO);
        historyDTO.setLastLookTime(LocalDateTime.now());

        //存在的话只要进行简单的更新即可
        if (id != -1) {
            historyDTO.setId(id);
            historyService.updateHistory(historyDTO);
        } else { //否则进行新增操作
            historyService.addHistory(historyDTO);
        }

        return Result.success();
    }
}
