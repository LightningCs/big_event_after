package com.s.bigevent.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收藏类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Collection {

    private Integer id;

    @NotNull
    private Integer articleId;

    @NotNull
    private Integer userId;

}
