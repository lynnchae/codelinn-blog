package org.lynn.springbootstarter.controller;

import org.lynn.springbootstarter.dao.UserDao;
import org.lynn.springbootstarter.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/echo")
    public Map echo(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("hello","hello,Spring-boot");
        return resultMap;
    }

    @GetMapping(value = "/getUser")
    public User getUser(){
        User user = new User();
        user.setName("lynn");
        return userDao.getByObject(user);
    }

    @GetMapping(value = "/getUsers")
    public List<User> getUsers(){
        User user = new User();
        user.setName("lynn");
        return userDao.query(user);
    }

}
