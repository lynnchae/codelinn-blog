package com.codelinn.blog.controller;

import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.Visitor;
import com.codelinn.blog.service.CommentService;
import com.codelinn.blog.service.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.PostMapping;
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
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();             //将加载多个绝对匹配的所有Resource
        //将首先通过ClassLoader.getResources("META-INF")加载非模式路径部分      //然后进行遍历模式匹配      Resource[] resources=resolver.getResources("classpath*:META-INF/INDEX.LIST");      Assert.assertTrue(resources.length > 1);          //将加载多个模式匹配的Resource
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources("classpath:static" + IMG_PATH + "*.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Resource r : resources) {
            headFileNameList.add(r.getFilename());
        }
    }

    @PostMapping("sendComment")
    public ResultEntity sendComment(Comment comment, HttpServletResponse response) throws IOException {
        Visitor visitor = new Visitor();
        visitor.setName(comment.getCommenter().trim());
        if (visitorService.count(visitor) > 0) {
            visitor = visitorService.query(visitor).get(0);
        } else {
            Integer fileNameIndex = new Random().nextInt(20) + 1;
            visitor.setAvatar(IMG_PATH + headFileNameList.get(fileNameIndex));
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
