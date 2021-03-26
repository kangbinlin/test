package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Menu;
import com._520it.crm.domain.Permission;
import com._520it.crm.service.IPermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionUtil {

    /*
     * Spring确实能给对象属性注入值
     * 但我们必须搞明白，什么是对象属性？非静态字段！！
     * 静态字段属于类，不属于对象。
     * 不信的话，大家可以创建一个Person类，设定两个字段 静态的age、非静态的name。
     * 结果你debug观察person对象只能看到name！
     * */
    private static IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        PermissionUtil.permissionService = permissionService;
    }

    /**
     * 你们可能觉得 超级管理员放行、ALL权限又放行，之间的逻辑是不是有点乱，感觉可以合并？其实仔细想想，确实有这样的需求的
     * @param function
     * @return
     */
    public static boolean checkPermission(String function) {

        System.out.println(function);
        /*
         * 如果是超级管理员直接放行
         *
         * 拿到当前系统所有权限资源（需要权限验证的url），判断当前方法是否包含在其中
         *   1.不包含：不需要权限判断，返回true，放行
         *   2.包含，则进一步判断当前用户是否拥有该权限
         *       1)拥有：返回true，放行
         *       2)没有：返回false，拦截
         *
         * */

        // 如果是超级管理员，直接放行
        Employee currentUser = (Employee) UserContext.get().getSession().getAttribute(UserContext.USER_IN_SESSION);
        if (currentUser.getAdmin()) {
            return true;
        }

        // 得到当前系统所有权限（需要权限验证的url的）
        if (CommonUtil.ALL_PERMISSIONS.size() == 0) {
            // 从数据库查询当前系统所有权限资源，封装到ALL_PERMISSIONS
            List<Permission> permissions = permissionService.selectAll();
            for (Permission permission : permissions) {
                CommonUtil.ALL_PERMISSIONS.add(permission.getResource());
            }
        }

        // 如果当前访问的url不在系统所有权限中，说明不需要验证，否则进行验证
        if (CommonUtil.ALL_PERMISSIONS.contains(function)) {
            // 当前访问的方法需要权限验证，所以要查看当前用户是否拥有该权限
            List<String> userPermissions = (List<String>) UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
            if (userPermissions.contains(function)) {
                return true;
            } else {
                // ALL权限匹配
                String allPermission = function.split(":")[0] + ":ALL";
                if (userPermissions.contains(allPermission)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            // 当前访问的方法不需要权限验证，直接放行
            return true;
        }
    }


    /**
     * 根据当前用户的权限，从全部菜单中筛选出用户能访问的菜单
     *
     * @param menuList
     */
    public static void checkMenuPermission(List<Menu> menuList) {

        /*
        * 不知道有没有人注意到，老师for循环遍历是反过来的。
        * 如果你是和我一样正向遍历，注意在remove后i--，否则会出错。
        * 具体原因，大家自己画图分析一下就知道了，刘意老师在迭代器还是集合那边有提到过。
        * 想视频里老师的做法，他的循环判断条件不受size影响，它只看i是否还大于0，即当前list是否还有元素，有就继续迭代
        *
        * */

        // 用户拥有的权限
        List<String> userPermissions = (List<String>) UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);

        // 遍历系统菜单，与当前用户拥有的权限进行比对
        for (int i = 0; i < menuList.size(); i++) {

            String menuPermission = menuList.get(i).getFunction();
            // 菜单需要访问权限
            if (StringUtils.isNotBlank(menuPermission)) {
                // 如果用户没有该菜单权限，删除它，这样前台就不会显示
                if (!userPermissions.contains(menuPermission)) {
                    menuList.remove(i);
                    // 注意，如果是从前往后遍历，一定要i--，否则会出错
                    i--;
                }
                // else 就说明用户有权限，那么就不做处理，菜单保留着
            }
            // else 说明该菜单根本不要求访问权限，谁来都可以访问，那么当前用户也可以，还是不做处理，菜单保留着

            // 递归处理子菜单
            List<Menu> childrenMenuList = menuList.get(i).getChildren();
            if (!childrenMenuList.isEmpty()) {
                checkMenuPermission(childrenMenuList);
            }
        }

    }
}
