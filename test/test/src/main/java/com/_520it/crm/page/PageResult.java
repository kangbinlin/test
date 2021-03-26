package com._520it.crm.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class PageResult {

    private Integer total;
    private List rows;

    public PageResult(Integer total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}


