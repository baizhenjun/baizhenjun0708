package com.example.java_springboot.batis.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页")
public class PageList {
    @ApiModelProperty(value = "当前页", required = true)
    private Integer current;

    @ApiModelProperty(value = "展示条数", required = true)
    private Integer size;
}
