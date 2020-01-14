package com.ivo.common.data;

import com.ivo.common.utils.HttpServletUtil;
import lombok.Data;

/**
 * 封装URL地址，自动添加应用上下文路径
 * @author wj
 * @version 1.0
 */
@Data
public class URL {

    private String url;

    /**
     * 封装URL地址，自动添加应用上下文路径
     * @param url URL地址
     */
    public URL(String url){
        this.url = HttpServletUtil.getRequest().getContextPath() + url;
    }
}
