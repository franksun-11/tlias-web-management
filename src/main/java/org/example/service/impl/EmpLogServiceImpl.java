package org.example.service.impl;

import org.example.mapper.EmpLogMapper;
import org.example.pojo.EmpLog;
import org.example.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    @Transactional(propagation = Propagation.REQUIRES_NEW) //需要在一个新的事务中运行,其他事务回滚不影响
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
