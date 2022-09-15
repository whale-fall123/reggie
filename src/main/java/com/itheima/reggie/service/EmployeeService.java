package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {

    R<Employee> login(HttpServletRequest request,  Employee employee);

    R<String> logout(HttpServletRequest request);

    R<String> save(HttpServletRequest request, Employee employee);

    R<Page> page(int page, int pageSize, String name);

    R<String> update(HttpServletRequest request, Employee employee);

    R<Employee> getById(@PathVariable Long id);

}
