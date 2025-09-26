package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 查询学员列表
     */
    List<Student> list(StudentQueryParam param);

    /**
     * 添加学员
     */
    void insert(Student student);
}
