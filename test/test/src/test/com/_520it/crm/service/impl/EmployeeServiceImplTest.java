package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class EmployeeServiceImplTest {

    /*
    * application.xml和application-mvc.xml请按照我的配置方式来：父子容器各自扫描！
    * 由于@ContextConfiguration("classpath:application.xml")要加载Spring容器
    * 如果你按老师的方式，测试方法行不通，因为老师把扫描工作全部交给了application-mvc.xml
    * */

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void deleteByPrimaryKey() {
        employeeService.deleteByPrimaryKey(1L);
    }

    @Test
    public void insert() {
        Employee employee = new Employee();
        employee.setUsername("777");
        employeeService.insert(employee);
    }

    @Test
    public void selectByPrimaryKey() {
        Employee employee = employeeService.selectByPrimaryKey(1L);
    }

    @Test
    public void selectAll() {
        employeeService.selectAll();
    }

    @Test
    public void updateByPrimaryKey() {
        Employee employee = new Employee();
        employee.setId(1L);
        employeeService.updateByPrimaryKey(employee);
    }
}