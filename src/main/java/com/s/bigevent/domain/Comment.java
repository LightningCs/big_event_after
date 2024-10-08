package com.s.bigevent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @NotNull
    private Integer id;

    @NotEmpty
    private String content;

    @NotNull
    private Integer articleId;

    @NotNull
    private Integer userId;

    @NotEmpty
    private String username;

    @NotEmpty
    private String userPic;

    @NotNull
    private Integer like;

    @NotNull
    private Integer dislike;

    private Integer responseCommentId;

    private List<Comment> responseList;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
