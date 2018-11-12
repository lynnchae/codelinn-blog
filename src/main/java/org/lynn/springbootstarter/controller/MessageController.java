package org.lynn.springbootstarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.model.Message;
import org.lynn.springbootstarter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Name : org.lynn.springbootstarter.controller
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
    public ResultEntity leavingMessage(Message message){
        messageService.insert(message);
        return ResultEntity.success("success");
    }

}
