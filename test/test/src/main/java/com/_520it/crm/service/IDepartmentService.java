package com._520it.crm.service;

import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DepartmentQueryObject;

import java.util.List;

public interface IDepartmentService {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    List<Department> queryForEmp();

    PageResult queryForPage(DepartmentQueryObject queryObject);

    int updateState(Long id);

//    Department getDepartmentForLogin(String username, String password);
//
//    PageResult queryForPage(DepartmentQueryObject queryObject);
}
