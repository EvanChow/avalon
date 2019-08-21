package com.avalon.common.avaloncommonzk.controller;

import com.avalon.common.avaloncommonzk.vo.Json;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {


    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        request.setAttribute("name","Avalon");
        return "/index";
    }

    @RequestMapping("/jump")
    public String Zookeeper(Model model,String html,String ip,String timeout,String sleep,String node) {
        model.addAttribute("ip",ip);
        model.addAttribute("timeout",timeout);
        model.addAttribute("sleep",sleep);
        model.addAttribute("node",node);
        if(html==null&&"".equals(html)){
            return "index";
        }
        return html;
    }

    @RequestMapping("/welcome")
    @ResponseBody
    public Json Welcome(String uid) {
        Json j=new Json(true,"ok","XXXXXXXXXXXXXx");
        return j;
    }


}
