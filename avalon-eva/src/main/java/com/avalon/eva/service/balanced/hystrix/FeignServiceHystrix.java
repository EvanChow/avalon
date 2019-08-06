package com.avalon.eva.service.balanced.hystrix;

import com.avalon.eva.service.balanced.FeignService;
import com.avalon.eva.vo.Json;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceHystrix implements FeignService {

    @Override
    public Json getJson() {
        System.out.println("服务降级。。。。。。。。");
        return new Json(false,"接口异常");
    }
}
