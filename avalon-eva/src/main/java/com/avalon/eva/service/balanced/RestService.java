package com.avalon.eva.service.balanced;

import com.avalon.eva.vo.Json;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * rest 访问接口
     * @return
     */
    @HystrixCommand(fallbackMethod = "errorBack")
    public Json getRestList(){
        return restTemplate.getForObject("http://avalon-eva-api:8087/hello",Json.class);
    }

    public Json errorBack(){
        return new Json(false,"接口异常");
    }


}
