package com._520it.crm.web.controller;

import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DepartmentQueryObject;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/department")
    public String index() {
        return "department";
    }

    @RequestMapping("/department_queryForEmployee")
    @ResponseBody
    public List<Department> queryForEmp() {
        // 虽然已经有自动生成的selectAll()，但是我们本次需求其实只要id和name，为了不浪费性能，另写一个（实际工作可能就直接用了，哈哈）
        return departmentService.queryForEmp();
    }


    @RequestMapping("/dep_list")
    @ResponseBody
    public PageResult list(DepartmentQueryObject queryObject) {
        PageResult pageResult = null;
        pageResult = departmentService.queryForPage(queryObject);

        return pageResult;
    }

    @RequestMapping("/dep_save")
    @ResponseBody
    public AjaxResult save(Department department) {

        AjaxResult result = null;
        try {
            departmentService.insert(department);
            result = new AjaxResult("保存成功", true);
        } catch (Exception e) {
            result = new AjaxResult("保存异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/dep_update")
    @ResponseBody
    public AjaxResult update(Department department) {

        AjaxResult result = null;
        try {
            departmentService.updateByPrimaryKey(department);
            result = new AjaxResult("更新功能未开发", true);
        } catch (Exception e) {
            result = new AjaxResult("更新异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/dep_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {

        AjaxResult result = null;
        try {
            departmentService.updateState(id);
            result = new AjaxResult("删除功能未开发", true);
        } catch (Exception e) {
            result = new AjaxResult("删除异常，请联系管理员", false);
        }
        return result;
    }

}
