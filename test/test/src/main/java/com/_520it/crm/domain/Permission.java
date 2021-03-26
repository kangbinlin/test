package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 这只做最简单的字段。
 * 如果你们公司有需求要根据权限找角色，可以在Permission中维护List<Role>
 */
@Getter@Setter
public class Permission {
    private Long id;

    private String name;

    private String resource;

}