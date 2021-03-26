package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(String username, String password);

    PageResult queryForPage(EmployeeQueryObject queryObject);

    int updateState(Long id);

    List<Long> queryRoleByEid(Long eid);
}
