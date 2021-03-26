package com._520it.crm.util;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AjaxResult {
    private String msg;
    private boolean success = false;

    public AjaxResult(String msg) {
        this.msg = msg;
    }

    public AjaxResult(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }
}
