package org.example.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {
    // 查询条件
    private String name;
    private Integer degree;
    private Integer clazzId;

    // 分页参数
    private Integer page = 1;
    private Integer pageSize = 10;
}
