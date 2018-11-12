package org.lynn.springbootstarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:16 PM
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("sendComment")
    public ResultEntity sendComment(Comment comment){
        commentService.insert(comment);
        return ResultEntity.success("success");
    }


}
