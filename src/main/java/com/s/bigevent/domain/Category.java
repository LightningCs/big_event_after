package com.s.bigevent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    /**
     * @NotNull:不能为 null，但可以为 empty，一般用在 Integer 类型的基本数据类型的非空校验上，
     *          而且被其标注的字段可以使用 @size、@Max、@Min 对字段数值进行大小的控制
     * @NotEmpty:不能为 null，且长度必须大于 0，一般用在集合类上或者数组上
     * */
    @NotNull(groups = {Update.class, Delete.class})
    private Integer id;//主键ID

    @NotEmpty
    private String categoryName;//分类名称

    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    //分组校验
    //定义校验项时如果没有定义分组，则属于Default分组，分组可以继承
    /**
     * 1.通过在实体类内定义接口来定义分组
     * 2.通过groups属性来对校验组分组
     * 3.通过给@Validated注解的value属性赋值来指定分组
     * 4.校验项默认属于Default分组
     *
     * */
    public interface Add extends Default {

    }

    public interface Update extends Default {

    }

    public interface Delete {

    }
}
