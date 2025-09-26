package org.example.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class Clazz {
    private Integer id;
    private String name;
    private String room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Long masterId;
    private String masterName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status; // 1.未开班 2.已开班 3.已结课
}
