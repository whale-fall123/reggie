package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /*用EmployeeService的话就要把接口的方法名改成getId*/
    /*@Autowired
    private EmployeeService employeeService;*/
    //以后不要自动装载调用自己，直接用this.

    //登录
    public R<Employee> login(HttpServletRequest request, Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeMapper.selectOne(queryWrapper);

        if(emp == null){
            return R.error("登录失败");
        }

        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    //退出登录
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    //新增成员
    public R<String> save(HttpServletRequest request, Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        /*employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);*/

        employeeMapper.insert(employee);
        return R.success("新增员工成功");
    }

    //分页查询
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);

        Page pageInfo = new Page(page,pageSize);


        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);

        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeMapper.selectPage(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    //修改信息
    public R<String> update(HttpServletRequest request, Employee employee){
        log.info(employee.toString());

        /*Long empId = (Long)request.getSession().getAttribute("employee");

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);*/
        employeeMapper.updateById(employee);

        return R.success("员工信息修改成功");

    }

    //根据id查询
    public R<Employee> getById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        if(employee != null){
            return R.success(employee);
        }
        return null;
    }
}
