package org.lynn.springbootstarter.controller;

import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.controller.response.SimpleBlogResponse;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:51
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

//    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Resource
    private BlogService blogService;

    @GetMapping("/getUserBlogs")
    public ResultEntity<List<SimpleBlogResponse>> getUserBlogs(Long userId){
        return ResultEntity.success(blogService.getUserBlogsWithoutContent(userId));
    }

    @PostMapping("/saveBlog")
    public ResultEntity<List<Blog>> saveBlog(Blog blog){
        return ResultEntity.success(blogService.insert(blog));
    }


}
