package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们这里只做单向的：角色包含权限，即根据角色可以得到角色所拥有的权限。
 * 之前在Permission老师说过，如果你们公司需要，也可以在Permission中维护Role，这样就是双向的
 *
 * 最后，如何根据角色查询角色所拥有的权限？请参考role.js中的edit方法
 */
@Getter
@Setter
public class Role {
    private Long id;

    private String name;

    private String sn;

    private List<Permission> permissions = new ArrayList<>();

}