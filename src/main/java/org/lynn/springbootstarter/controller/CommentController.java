package org.lynn.springbootstarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.model.Visitor;
import org.lynn.springbootstarter.service.CommentService;
import org.lynn.springbootstarter.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

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

    @Autowired
    private VisitorService visitorService;

    @PostMapping("sendComment")
    public ResultEntity sendComment(Comment comment, HttpServletResponse response) throws IOException {
        Visitor visitor = new Visitor();
        visitor.setName(comment.getCommenter().trim());
        if(visitorService.count(visitor) > 0){
            visitor = visitorService.query(visitor).get(0);
        }else{
            Integer fileNameIndex = new Random().nextInt(20) + 1;
            visitor.setAvatar("/images/head/"+ fileNameIndex +".png");
            visitorService.insert(visitor);
        }
        try {
            comment.setVisitorId(visitor.getId());
            commentService.insert(comment);
            return ResultEntity.success(visitor.getAvatar());
        } catch (Exception e) {
            response.sendError(500, "post comment error!");
            log.error("insert comment error, para {} , e {}", comment, e);
            return ResultEntity.error(500, "post comment error");
        }
    }

}
