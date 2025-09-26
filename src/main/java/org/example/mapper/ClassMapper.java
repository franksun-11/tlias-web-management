package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;

import java.util.List;

@Mapper
public interface ClassMapper {
    /**
     * 查询所有班级
     */
    List<Clazz> list(ClassQueryParam classQueryParam);
}
