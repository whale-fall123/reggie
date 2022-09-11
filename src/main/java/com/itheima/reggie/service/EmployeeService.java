package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.commen.R;
import com.itheima.reggie.entity.Employee;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {

    R<Employee> login(HttpServletRequest request, @RequestBody Employee employee);

    R<String> logout(HttpServletRequest request);
}
