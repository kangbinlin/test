package com._520it.crm.service;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

/**
 * 由于我们选择角色和权限分开查（先查角色，需要时单独根据rid查询拥有的权限），所以查询方法都很简单。
 * 但是增删改因为牵扯到中间表，会稍微麻烦些
 */
public interface IRoleService {
    /**
     * 要先删除中间表，否则role是删不掉的！
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 除了插入role表，还需要维护role_permission关联表！
     * @param record
     * @return
     */
    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    /**
     * 除了更新role表，还要维护role_permission关联表！
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

    PageResult queryForPage(QueryObject queryObject);
}
