package com.avalon.eva.service.balanced;

import com.avalon.eva.service.balanced.hystrix.FeignServiceHystrix;
import com.avalon.eva.vo.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "avalon-eva-api", fallback = FeignServiceHystrix.class )
public interface FeignService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    Json getJson();

}
