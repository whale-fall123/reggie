package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class  EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //登录
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        R<Employee> data = employeeService.login(request, employee);
        return data;
    }

    //退出登录
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        R<String> logout = employeeService.logout(request);
        return logout;
    }

    //新增成员
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        R<String> save = employeeService.save(request, employee);
        return save;
    }

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        R<Page> r = employeeService.page(page, pageSize, name);
        return r;
    }

    //修改信息
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        R<String> update = employeeService.update(request, employee);
        return update;
    }

    //根据id查询
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        R<Employee> employee = employeeService.getById(id);
        return employee;
    }
}