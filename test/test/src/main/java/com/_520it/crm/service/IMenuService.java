package com._520it.crm.service;

import com._520it.crm.domain.Menu;
import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IMenuService {

    List<Menu> queryForMenu();
}
