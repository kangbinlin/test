package com._520it.crm.mapper;

import com._520it.crm.domain.Role;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int insertRelation(@Param("rid") Long rid, @Param("pid") Long pid);

    Long queryForPageCount(QueryObject queryObject);

    List<Role> queryForPage(QueryObject queryObject);

    int deletePermissionByRid(Long id);
}
