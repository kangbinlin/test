package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Menu;
import com._520it.crm.domain.Permission;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IMenuService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.service.IRoleService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.UserContext;
import com.alibaba.druid.sql.PagerUtils;
import com.sun.media.sound.EmergencySoundbank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/employee")
    public String index() {
        return "employee";
    }

    @RequestMapping("login")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request) {

        //---------AOP日志相关：把request放入当前线程-----------
        UserContext.set(request);

        //---------登录相关-------
        AjaxResult result = null;
        Employee currentUser = employeeService.getEmployeeForLogin(username, password);
        if (currentUser != null) {
            result = new AjaxResult("登录成功", true);
            // 把用户信息存入session
            request.getSession().setAttribute(UserContext.USER_IN_SESSION, currentUser);
            // 把用户拥有的权限放入session中
            List<String> userPermissions = permissionService.queryResourceByEid(currentUser.getId());
            request.getSession().setAttribute(UserContext.PERMISSION_IN_SESSION, userPermissions);
            /*
            * 把用户能访问的菜单存入session
            *   1.先查出系统所有菜单
            *   2.根据当前用户拥有的权限，筛选出用户专属菜单
            * */
            List<Menu> menuList = menuService.queryForMenu();
            PermissionUtil.checkMenuPermission(menuList);// 该方法会从全部菜单中剔除用户无权访问的菜单
            request.getSession().setAttribute(UserContext.MENU_IN_SESSION,menuList);
        } else {
            result = new AjaxResult("账号或密码错误", false);
        }

        return result;
    }

    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject queryObject) {
        PageResult pageResult = null;
        pageResult = employeeService.queryForPage(queryObject);

        return pageResult;
    }

    @RequestMapping("/employee_save")
    @ResponseBody
    public AjaxResult save(Employee employee) {

        AjaxResult result = null;
        // 这里选择了抛异常来手机错误信息。有些人可能会判断insert()的返回值是否为空来判断，个人觉得不是很好
        try {
            // 前端只输入了部分字段，剩余字段后端默认设置（密码，是否管理员，状态）
            employee.setPassword("123");
            employee.setAdmin(false);
            employee.setState(true);
            employeeService.insert(employee);
            result = new AjaxResult("保存成功", true);
        } catch (Exception e) {
            result = new AjaxResult("保存异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/employee_update")
    @ResponseBody
    public AjaxResult update(Employee employee) {

        AjaxResult result = null;
        try {
            /*
            * 老师直接把updateByPrimaryKey的sql多余字段删了
            * 其实可以使用动态sql中的if语句，当值不为null时才执行
            * 这样下次你要改密码，只要把密码传进去即可
            * */
            employeeService.updateByPrimaryKey(employee);
            result = new AjaxResult("更新成功", true);
        } catch (Exception e) {
            result = new AjaxResult("更新异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/employee_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {

        AjaxResult result = null;
        try {
            /*
            * 还是和上面的update同一个问题，如果用动态sql
            * 这里只需要设置员工的state，后台就可以更新了，不用另外写方法
            * */
            employeeService.updateState(id);
            result = new AjaxResult("删除成功", true);
        } catch (Exception e) {
            result = new AjaxResult("删除异常，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role_queryByEid")
    @ResponseBody
    public List queryByEid(Long eid) {
        return employeeService.queryRoleByEid(eid);
    }

}
