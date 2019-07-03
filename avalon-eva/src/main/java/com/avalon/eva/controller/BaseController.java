package com.avalon.eva.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    @Value("${info.version}")
    private String version;

    @Value("${info.name}")
    private String name;

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        request.setAttribute("name",version);
        request.setAttribute("version",name);
        return "index";
    }

}
