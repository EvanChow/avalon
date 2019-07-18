package com.avalon.eva.controller.balanced;

import com.avalon.eva.service.balanced.RestService;
import com.avalon.eva.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @Autowired
    private RestService restService;

    @RequestMapping("/getrestdate")
    @ResponseBody
    public Json getData(){
        return restService.getRestList();
    }

}
