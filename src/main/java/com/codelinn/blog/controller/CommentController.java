package com.codelinn.blog.controller;

import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.Visitor;
import com.codelinn.blog.service.CommentService;
import com.codelinn.blog.service.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Name : com.codelinn.blog.controller
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

    private static List<String> headFileNameList;

    private static String IMG_PATH = "/images/head/";

    static {
        headFileNameList = new ArrayList<>();
        headFileNameList.add("https://pic.codelinn.com//random/header1.png");
        headFileNameList.add("https://pic.codelinn.com//random/header2.png");
        headFileNameList.add("https://pic.codelinn.com//random/header3.png");
        headFileNameList.add("https://pic.codelinn.com//random/header4.png");
        headFileNameList.add("https://pic.codelinn.com//random/header5.png");
        headFileNameList.add("https://pic.codelinn.com//random/header6.png");
        headFileNameList.add("https://pic.codelinn.com//random/header7.png");
        headFileNameList.add("https://pic.codelinn.com//random/header8.png");
        headFileNameList.add("https://pic.codelinn.com//random/header9.png");
        headFileNameList.add("https://pic.codelinn.com//random/header10.png");
        headFileNameList.add("https://pic.codelinn.com//random/header11.png");
        headFileNameList.add("https://pic.codelinn.com//random/header12.png");
        headFileNameList.add("https://pic.codelinn.com//random/header13.png");
        headFileNameList.add("https://pic.codelinn.com//random/header14.png");
        headFileNameList.add("https://pic.codelinn.com//random/header15.png");
        headFileNameList.add("https://pic.codelinn.com//random/header16.png");
        headFileNameList.add("https://pic.codelinn.com//random/header17.png");
        headFileNameList.add("https://pic.codelinn.com//random/header18.png");
        headFileNameList.add("https://pic.codelinn.com//random/header19.png");
        headFileNameList.add("https://pic.codelinn.com//random/header20.png");
        headFileNameList.add("https://pic.codelinn.com//random/header21.png");
        headFileNameList.add("https://pic.codelinn.com//random/header22.png");
        headFileNameList.add("https://pic.codelinn.com//random/header23.png");
        headFileNameList.add("https://pic.codelinn.com//random/header24.png");
    }

    @PostMapping(value = "sendComment",produces = "application/json")
    public ResultEntity sendComment(@RequestBody Comment comment, HttpServletResponse response) throws IOException {
        Visitor visitor = new Visitor();
        visitor.setName(comment.getCommenter().trim());
        if (visitorService.count(visitor) > 0) {
            visitor = visitorService.query(visitor).get(0);
        } else {
            Integer fileNameIndex = new Random().nextInt(24) + 1;
            visitor.setAvatar(headFileNameList.get(fileNameIndex));
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
