package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /**
     * 原始分页查询
     * @param page
     * @param pageSize
     * @return
     */
/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize){
        // 1.调用mapper接口查询总记录数
        Long total = empMapper.count();
        // 2.调用mapper接口查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        // 3.封装结果
        return new PageResult<Emp>(total, rows) ;
    }*/

    /**
     * pageHelper分页查询
     * @return
     * 注意事项：
     *        1.定义的SQL语句结尾不能加分号
     *        2.PageHelper仅仅能对紧跟在其后的第一个select有效
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam){
        // 1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3.执行查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }


    /**
     * 保存员工信息
     */
    @Transactional(rollbackFor = {Exception.class}) //事务管理 -默认出现运行时异常时(RuntimeException)回滚, 可以通过rollbackFor指定回滚的异常类型
    @Override
    public void save(Emp emp){
        // 1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        // 2.保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            // 遍历集合, 为empId赋值
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
