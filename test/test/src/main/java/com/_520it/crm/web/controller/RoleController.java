package com._520it.crm.web.controller;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.service.IRoleService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/role")
    public String role() {
        return "role";
    }

    @RequestMapping("/role_save")
    @ResponseBody
    public AjaxResult save(Role role) {

        AjaxResult result = null;
        try {
            roleService.insert(role);
            result = new AjaxResult("保存成功", true);
        } catch (Exception e) {
            result = new AjaxResult("保存异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role_list")
    @ResponseBody
    public PageResult list(RoleQueryObject queryObject) {
        PageResult pageResult = null;
        pageResult = roleService.queryForPage(queryObject);
        return pageResult;
    }

    @RequestMapping("/role_update")
    @ResponseBody
    public AjaxResult update(Role role) {

        AjaxResult result = null;
        try {
            roleService.updateByPrimaryKey(role);
            result = new AjaxResult("更新成功", true);
        } catch (Exception e) {
            result = new AjaxResult("更新异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {

        AjaxResult result = null;
        try {
            roleService.deleteByPrimaryKey(id);
            result = new AjaxResult("删除成功", true);
        } catch (Exception e) {
            result = new AjaxResult("删除异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role_queryForEmployee")
    @ResponseBody
    public List queryForEmployee() {
        return roleService.selectAll();
    }



}
