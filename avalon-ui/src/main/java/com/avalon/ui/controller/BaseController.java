package com.avalon.ui.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        request.setAttribute("name",name);
        request.setAttribute("version",version);
        return "index";
    }


    @RequestMapping("/jump")
    public String jump(Model model, String html){

        if(html==null&&"".equals(html)){
            return "index";
        }
        return html;
    }

}
