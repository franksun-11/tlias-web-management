package org.example.service;

import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    /**
     * 分页查询班级
     */
    PageResult<Clazz> page(ClassQueryParam classQueryParam);

    /**
     *添加班级
     */
    void save(Clazz clazz);

    /**
     * 根据id查询班级
     */
    Clazz findById(Integer id);

    /**
     * 删除班级
     */
    void deleteById(Integer id);

    /**
     * 查询所有班级
     */
    List<Clazz> list();

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);
}
