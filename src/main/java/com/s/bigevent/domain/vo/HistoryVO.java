package com.s.bigevent.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVO {
    private Integer id; //历史记录id

    @NotNull
    private Integer articleId; //文章id

    @NotEmpty
    private String title; //文章标题

    @NotEmpty
    private String coverImg; //文章封面

    @NotNull
    private Integer userId; //用户id

    @NotNull
    private Integer createUser; //作者id

    @NotEmpty
    private String createUsername; //作者名称

    private String userPic; //作者头像

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLookTime; //上次访问时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //文章创建时间
}
