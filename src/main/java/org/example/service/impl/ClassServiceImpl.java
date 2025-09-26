package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClassMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.ClassQueryParam;
import org.example.pojo.Clazz;
import org.example.pojo.Emp;
import org.example.pojo.PageResult;
import org.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private EmpMapper empMapper;
    /**
     * 分页查询班级
     */
    @Override
    public PageResult<Clazz> page(ClassQueryParam classQueryParam) {
        // 1.设置分页参数
        PageHelper.startPage(classQueryParam.getPage(), classQueryParam.getPageSize());
        // 2.执行查询
        List<Clazz> clazzList =classMapper.list(classQueryParam);
        // 3.1 遍历clazzList, 给每一个class根据当前时间与课程时间关系设置状态
        clazzList.forEach(clazz -> {
                    if (clazz.getEndDate().isBefore(LocalDate.now())) {
                        clazz.setStatus("已结课");
                    }
                    else if (clazz.getBeginDate().isAfter(LocalDate.now())) {
                        clazz.setStatus("未开班");
                    }
                    else {
                        clazz.setStatus("在读中");
                    }
                });
        // 3.2 根据masterId在emp表查询出班主任(员工)姓名

        clazzList.forEach(clazz -> {
            Emp emp = empMapper.findById(clazz.getMasterId());
            if(emp != null){
                clazz.setMasterName(emp.getName());
            } else {
                clazz.setMasterName("未知班主任");
            }
        });

        // 4.封装结果并返回
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 添加班级
     */
    @Override
    public void save(Clazz clazz) {
        // 1.补全创建时间,修改时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        // 2.设置status,若当前时间
        // - 当前时间 > 结课时间：状态 已结课。
        //  - 当前时间 < 开课时间：状态 未开班。
        //  - 否则，就是 在读中。
        if (clazz.getEndDate().isBefore(LocalDate.now())) {
            clazz.setStatus("已结课");
        }
        else if (clazz.getBeginDate().isAfter(LocalDate.now())) {
            clazz.setStatus("未开班");
        }
        else {
            clazz.setStatus("在读中");
        }
        // 3.保存
        classMapper.insert(clazz);
    }
}
