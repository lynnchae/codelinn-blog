package org.lynn.springbootstarter.controller;

import com.alibaba.druid.util.HttpClientUtils;
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
    public ResultEntity leavingMessage(Message message) {
        messageService.insert(message);
        StringBuffer param = new StringBuffer("text=");
        param.append("站内信--来自<").append(message.getSender()).append(">").append("&desp=").append(message.getMessage());
        try {
            HttpClientUtils.post("https://sc.ftqq.com/SCU36514Tab24b05f1de7f799500afff8bbe4d2af5bfcf72467b44.send"
                    , param.toString(), 5000);
        } catch (Exception e) {
            log.warn("serverchan sendmessage error, e {}", e);
        }
        return ResultEntity.success("success");
    }

}
