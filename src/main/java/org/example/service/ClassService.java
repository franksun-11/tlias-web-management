package org.example.service;

import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface ClassService {
    /**
     * 分页查询班级
     */
    PageResult<Clazz> page(ClassQueryParam classQueryParam);
}
