package com.example.java_springboot.batis.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页")
public class PageListReq<T> {
        /**
         * 实体类
         */
        @ApiModelProperty(value = "实体类",required = true)
        private T condition;
        /**
         * 当前页
         */
        @ApiModelProperty(value = "当前页",required = true)
        private Integer current;
        /**
         * 展示条数
         */
        @ApiModelProperty(value = "展示条数",required = true)
        private Integer size;
}
