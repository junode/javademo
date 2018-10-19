package com.demo.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by toutou on 2018/10/18.
 */
@Controller
public class MyErrorController {

    @RequestMapping("error404")
    public String error404(){
        return "404";
    }
    @RequestMapping("error500")
    public String error500(){
        return "500";
    }
}
