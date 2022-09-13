package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {

    R<Employee> login(HttpServletRequest request, @RequestBody Employee employee);

    R<String> logout(HttpServletRequest request);

    R<String> save(HttpServletRequest request,@RequestBody Employee employee);

    R<Page> page(int page, int pageSize, String name);

    R<String> update(HttpServletRequest request,@RequestBody Employee employee);

    R<Employee> getById(@PathVariable Long id);

}
