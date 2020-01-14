package com.ivo.system.controller;

import com.ivo.common.constant.ViewMapperConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wj
 * @version 1.0
 */
@Controller
public class MainController {

    /**
     * 后台主体内容
     */
    @GetMapping("/")
    public String main() {
        return ViewMapperConst.view_index;
    }

}
