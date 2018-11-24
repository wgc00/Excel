package com.wgc.uploadAndDownload.service;

import com.wgc.uploadAndDownload.dao.EmployeeMapper;
import com.wgc.uploadAndDownload.entity.Employee;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findEmps() {

        return employeeMapper.selectAll();
    }

    @Override
    public int insertData(List<Employee> employees) {
        int i = employeeMapper.batchInsert(employees);
        return i;
    }

    @Override
    public byte[] exportToExcel(List<Employee> employees) {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("员工编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("学历");
        row.createCell(4).setCellValue("月薪");

        for (int i = 0; i < employees.size(); i++) {
            XSSFRow rows = sheet.createRow(i + 1);
            Employee employee = employees.get(i);
            rows.createCell(0).setCellValue(employee.getNumber());
            rows.createCell(1).setCellValue(employee.getName());
            rows.createCell(2).setCellValue(employee.getGender());
            rows.createCell(3).setCellValue(employee.getEducation());
            rows.createCell(4).setCellValue(employee.getMonthlyPay().doubleValue());
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            workbook.write(stream);
            return stream.toByteArray();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Employee> importDatabase(FileInputStream fis) throws IOException {
        //创建一个Excel表
        List<Employee> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(fis);  //这里可以定义一个try来解决是否创建成功
        XSSFSheet sheet = workbook.getSheetAt(0);//获取第1个工作表
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {//循环Excel文件的每一行
            XSSFRow row = sheet.getRow(i);//获取第i行
            XSSFCell cell1 = row.getCell(0);//获取第一个单元格
            XSSFCell cell2 = row.getCell(1);
            XSSFCell cell3 = row.getCell(2);
            XSSFCell cell4 = row.getCell(3);
            XSSFCell cell5 = row.getCell(4);

            Employee emp = new Employee();

            Integer number = (int) cell1.getNumericCellValue();
            String name = cell2.getStringCellValue();//名字
            String gender = cell3.getStringCellValue();//性别
            String education = cell4.getStringCellValue();//学历
            BigDecimal decimal = new BigDecimal(cell5.getNumericCellValue());

            emp.setNumber(number);
            emp.setName(name);
            emp.setGender(gender);
            emp.setEducation(education);
            emp.setMonthlyPay(decimal);

            list.add(emp);
        }
        fis.close();
        return list;
    }
}
