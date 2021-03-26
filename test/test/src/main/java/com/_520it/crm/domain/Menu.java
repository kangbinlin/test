package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class Menu {
    private Long id;

    // 菜单名称
    private String text;

    private String iconCls;

    private Boolean checked;

    private String state;

    // 当菜单展示在页面后，点击该菜单发出的请求url
    private String attributes;

    // 不要觉得惊讶。数据库虽然是parent_id，但那只是为了表征父节点
    // 实际字段要看xml中sql是怎么查的，以及前台需要什么格式
    private List<Menu> children;

    // 访问当前菜单需要的权限。不要和上面的attributes搞混了额
    private String function;
}