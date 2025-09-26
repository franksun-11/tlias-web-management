package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    /**
     * 分页查询学生
     */
    PageResult<Student> page(StudentQueryParam param);
}
