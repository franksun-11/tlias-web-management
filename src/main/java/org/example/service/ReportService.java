package org.example.service;

import org.example.pojo.JobOption;

public interface ReportService {
    /**
     * 统计员工职位人数
     */
    JobOption getEmpJobData();
}
