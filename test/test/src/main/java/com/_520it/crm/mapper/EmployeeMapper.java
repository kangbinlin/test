package com._520it.crm.mapper;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(@Param("username") String username, @Param("password") String password);

    Long queryForPageCount(EmployeeQueryObject queryObject);

    List<Employee> queryForPage(EmployeeQueryObject queryObject);

    int updateState(Long id);

    void insertRelation(@Param("eid")Long eid, @Param("rid")Long rid);

    List<Long> queryRoleByEid(Long eid);

    int deleteRelation(Long eid);
}