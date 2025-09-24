package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.EmpExpr;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工工作经历数据
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 批量删除员工经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}
