package com.s.bigevent.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO {
    private Integer id;

    @NotNull
    private Integer articleId;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer createUser;

    @NotEmpty
    private LocalDateTime lastLookTime;
}
