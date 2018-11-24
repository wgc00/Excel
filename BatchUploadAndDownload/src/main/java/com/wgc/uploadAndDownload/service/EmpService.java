package com.wgc.uploadAndDownload.service;

import com.wgc.uploadAndDownload.entity.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public interface EmpService {

    //查询所有数据
    List<Employee> findEmps();

    int insertData(List<Employee> employees);

    byte[] exportToExcel(List<Employee> employees);

    List<Employee> importDatabase(FileInputStream fis) throws IOException;

}
