package com.codelinn.blog.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.model.User;
import com.codelinn.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.core.util.StrUtil.isBlank;

/**
 * Class Name : com.codelinn.blog.controller.dto
 * Description :
 *
 * @author : LiNn Cai
 * Date : 2019/12/7 1:48 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static String CLIENT_ID = "6efcfbe7062c229dc622";

    private static String CLIENT_SECRET = "0dcbbc1f26b96f0c10a85308fb7f88b43cb97e7a";

    @Resource
    private UserService userService;

    @GetMapping("/info")
    public ResultEntity<User> userInfo(@RequestParam String accessToken) {
        User user = new User();
        if(isBlank(accessToken)){
            return ResultEntity.success(user);
        }
        user.setToken(accessToken);
        return ResultEntity.success(userService.getByObject(user));
    }

    @PostMapping("/auth/github")
    public ResultEntity<User> githubOauth(String code) {
        Map<String, Object> param = new HashMap<>();
        param.put("client_id", CLIENT_ID);
        param.put("client_secret", CLIENT_SECRET);
        param.put("code", code);
        String resp = HttpUtil.post("https://github.com/login/oauth/access_token", param);
        String accessToken = HttpUtil.decodeParamMap(resp, "utf-8").get("access_token");
        HttpRequest httpRequest = HttpUtil.createGet("https://api.github.com/user").header("Authorization", "token " + accessToken);
        JSONObject userinfoJson = JSONObject.parseObject(httpRequest.execute().body());
        String avatarUrl = userinfoJson.getString("avatar_url");
        String name = userinfoJson.getString("name");
        Long userId = userinfoJson.getLong("id");
        String email = userinfoJson.getString("email");
        User user = new User();
        user.setUserId(userId);
        if(userService.count(user) > 0){
            user = userService.getByObject(user);
            return ResultEntity.success(user);
        }
        user.setName(name);
        user.setEmail(email);
        user.setToken(accessToken);
        user.setAvatarUrl(avatarUrl);
        userService.insert(user);
        return ResultEntity.success(user);
    }

}
