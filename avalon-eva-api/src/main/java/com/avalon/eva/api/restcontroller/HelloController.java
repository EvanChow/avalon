package com.avalon.eva.api.restcontroller;

import com.avalon.eva.api.vo.Json;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Json getHello(){
        return new Json(true,"返回成功","2333333");
    }

    @RequestMapping("/world")
    public Json getIndex(){
        return new Json(true,"返回成功","wohenhao");
    }

}
