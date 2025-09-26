package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClassMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam param) {
        // 1. 启动分页
        PageHelper.startPage(param.getPage(), param.getPageSize());
        // 2. 查询
        List<Student> students = studentMapper.list(param);
        // 3. 为每个学员补充 clazzName（班级名称）
        students.forEach(stu -> {
            if (stu.getClazzId() != null) {
                Clazz clazz = classMapper.findById(stu.getClazzId());
                if (clazz != null) {
                    stu.setClazzName(clazz.getName());
                } else {
                    stu.setClazzName("未知班级");
                }
            } else {
                stu.setClazzName("未分班");
            }
        });
        // 4. 封装分页结果
        Page<Student> p = (Page<Student>) students;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Student student) {
        // 1. 补全基础属性 createTime, updateTime
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        // 2. 根据clazzId查询班级信息
        Clazz clazz = classMapper.findById(student.getClazzId());
        if (clazz != null) {
            student.setClazzName(clazz.getName());
        } else {
            student.setClazzName("未知班级");
        }
        // 3.设置默认值
        student.setViolationCount(Short.valueOf("0"));
        student.setViolationScore(Short.valueOf("0"));
        // 4. 调用mapper的方法插入数据
        studentMapper.insert(student);
    }

}