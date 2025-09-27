package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学员分页条件查询
     */
    @GetMapping
    public Result page(StudentQueryParam param) {
        log.info("学员分页查询：{}", param);
        PageResult<Student> pageResult = studentService.page(param);
        return Result.success(pageResult);
    }

    /**
     * 新增学员
     */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("新增学员：{}", student);
        studentService.save(student);
        return Result.success(null);
    }

    /**
     * 根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员：{}", id);
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    /**
     * 修改学员
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员：{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 批量删除学员
     * 示例：DELETE /students/1,2,3
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除学员：{}", ids);
        studentService.deleteByIds(ids);
        return Result.success(null);
    }




}