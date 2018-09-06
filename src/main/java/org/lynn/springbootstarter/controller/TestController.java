package org.lynn.springbootstarter.controller;

import org.lynn.springbootstarter.common.BaseException;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.dao.UserDao;
import org.lynn.springbootstarter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/echo")
    public Map echo() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("hello", "hello,Spring-boot");
        return resultMap;
    }

    @GetMapping(value = "/getUser")
    public ResultEntity<User> getUser() {
        User user = new User();
        user.setName("lynn");
        return ResultEntity.success(userDao.getByObject(user));
    }

    @GetMapping(value = "/getUsers")
    public ResultEntity<List<User>> getUsers() {
        User user = new User();
        return ResultEntity.success(userDao.query(user));
    }

    @GetMapping(value = "/insertUser")
    public ResultEntity insertUser(User user) {
        if (user == null) {
            throw new BaseException(0,"user is empty");
        }
        return ResultEntity.success(userDao.insert(user));
    }

    @GetMapping(value = "/getByUserId")
    public ResultEntity getByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        return ResultEntity.success(userDao.getByUserId(userId));
    }

    @GetMapping(value = "/getById")
    public ResultEntity getById(Long id) {
        if (id == null) {
            return null;
        }
        return ResultEntity.success(userDao.getById(id));
    }

    @GetMapping(value = "/testException")
    public ResultEntity testException() {
        throw new BaseException(0,"rpc invoke error!");
    }

}
