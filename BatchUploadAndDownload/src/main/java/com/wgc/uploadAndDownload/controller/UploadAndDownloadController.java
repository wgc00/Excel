package com.wgc.uploadAndDownload.controller;


import com.wgc.uploadAndDownload.entity.Employee;
import com.wgc.uploadAndDownload.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UploadAndDownloadController {

    @Autowired
    private EmpService empService;


    @GetMapping("/queryAll")
    public String selectAll(Model model) {
        // employees = employeeMapper.selectAll();
        List<Employee> emps = empService.findEmps();
        model.addAttribute("emp", emps);
        return "index";
    }

    @GetMapping("/importData")
    public ResponseEntity<byte[]> importDataInfo() {
        List<Employee> emps;
        try {
            emps = empService.findEmps();
        } catch (Exception ex) {
            return ResponseEntity.ok("err".getBytes(StandardCharsets.ISO_8859_1));
        }

        byte[] contents = contents = empService.exportToExcel(emps);
        if (contents == null) {
            return ResponseEntity.ok("err".getBytes(StandardCharsets.ISO_8859_1));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(contents.length);
        headers.setContentDispositionFormData("attachment", "xxx_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + ".xlsx");
        return ResponseEntity.ok().headers(headers).body(contents);
    }


    /*导入数据库*/
    @GetMapping("/insert")
    public String excelOutPut() throws IOException {
        //存数据
        FileInputStream fis = new FileInputStream(new File("d://employee.xlsx"));
        //插入数据
        try {
            List<Employee> employees = empService.importDatabase(fis);
            empService.insertData(employees);
        }catch (Exception e){
            return "error";
        }
        return "redirect:/queryAll";

    }

}

