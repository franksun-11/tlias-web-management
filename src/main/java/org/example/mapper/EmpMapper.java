package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp e left outer join dept d on e.dept_id = d.id")
    public Long count();

    /**
     * 分页查询
     */
/*
    @Select("select e.*, d.name deptName from emp e left outer join dept d on e.dept_id = d.id " +
            "order by e.update_time desc limit #{start}, #{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);
*/

    /**
     * pageHelper分页查询
     */
    /*@Select("select e.*, d.name deptName from emp e left outer join dept d on e.dept_id = d.id order by e.update_time desc")*/
    /*List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);*/

    List<Emp> list(EmpQueryParam empQueryParam);

}
