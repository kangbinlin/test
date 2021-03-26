package com._520it.crm.service.impl;


import com._520it.crm.domain.Menu;
import com._520it.crm.mapper.MenuMapper;
import com._520it.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 如果使用idea，在类名上右键 goto 即可自动创建测试类
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryForMenu() {
        return menuMapper.queryForMenu();
    }
}
