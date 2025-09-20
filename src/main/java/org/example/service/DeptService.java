package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param deptId
     * @return
     */
    Dept getById(Integer deptId);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
