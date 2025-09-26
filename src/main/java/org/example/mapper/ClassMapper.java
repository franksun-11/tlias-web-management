package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;

import java.util.List;

@Mapper
public interface ClassMapper {
    /**
     * 查询所有班级
     */
    List<Clazz> list(ClassQueryParam classQueryParam);

    /**
     * 添加班级
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取生成的主键 -- 主键返回
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time)"
    + " values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject},#{createTime}, #{updateTime})")
    void insert(Clazz clazz);
}
