package org.lynn.springbootstarter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/3 15:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/echo")
    public Map echo(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("hello","hello,Spring-boot");
        return resultMap;
    }

}
