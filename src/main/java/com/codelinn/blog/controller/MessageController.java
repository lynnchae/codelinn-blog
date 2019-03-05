package com.codelinn.blog.controller;

import cn.hutool.http.HttpUtil;
import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.model.Message;
import com.codelinn.blog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name : com.codelinn.blog.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 12:27 PM
 */
@RestController()
@Slf4j
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/leavingMessage")
    public ResultEntity leavingMessage(Message message) {
        messageService.insert(message);
        Map<String, Object> param = new HashMap<>();
        param.put("text", "codelinn站内信--来自（" + message.getSender() + "）");
        param.put("desp", message.getMessage());
        try {
            String url = "https://sc.ftqq.com/SCU36514T84104f04eeffb86aa9baa794c763d8b15c7e3f57cf400.send";
            String result = HttpUtil.post(url, param);
            log.info("serverchan message url {}, pushed result {}", url, result);
        } catch (Exception e) {
            log.warn("serverchan sendmessage error, e {}", e);
        }
        return ResultEntity.success("success");
    }


}
