package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 根据id查询学员
     */
    @Select("select * from student where id = #{id}")
    Student findById(Integer id);

    /**
     * 修改学员信息
     */
    void updateById(Student student);
}
