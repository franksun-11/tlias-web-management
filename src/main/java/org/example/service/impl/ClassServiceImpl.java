package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClassMapper;
import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    /**
     * 分页查询班级
     */
    @Override
    public PageResult<Clazz> page(ClassQueryParam classQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());
        // 2.执行查询
        List<Clazz> clazzList =classMapper.list(classQueryParam);
        // 3.封装结果并返回
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
