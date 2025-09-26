package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 分页查询班级
     */
    @GetMapping
    public Result page(ClassQueryParam classQueryParam){
        log.info("分页查询班级数据:{},{},{},{},{}" + classQueryParam);
        PageResult<Clazz> pageResult =classService.page(classQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加班级
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级：{}", clazz);
        classService.save(clazz);
        return Result.success();
    }
}
