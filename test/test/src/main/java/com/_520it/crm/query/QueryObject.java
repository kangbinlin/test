package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class QueryObject {
    /*
    * 我知道你进来是想改start，但是建议不要：
    * 1.这样做比较恶心...
    * 2.左侧要分页我没意见，但是你要想想最极端的情况是左侧的权限都到右侧，也就是说左右两侧最大数量是一样的，那右侧为啥不加分页？
    *
    * 所以大家去看看我role.js中，selfPermissions我也加了分页，这样向后台请求时会自动带上分页信息，合理又美观！
    *
    * */
    private Integer page;
    private Integer rows;
    public Integer getStart() {
        return (page - 1) * rows;
    }
}
