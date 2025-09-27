package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    /**
     * 分页查询学生
     */
    PageResult<Student> page(StudentQueryParam param);

    /**
     * 添加学生
     */
    void save(Student student);

    /**
     * 根据id查询学生
     */
    Student findById(Integer id);

    /**
     * 修改学生信息
     */
    void update(Student student);

    /**
     * 批量删除学生
     */
    void deleteByIds(List<Integer> ids);
}
