package org.lynn.springbootstarter.controller;

import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.controller.response.SimpleBlogResponse;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.service.BlogService;
import org.pegdown.PegDownProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:51
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

//    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Resource
    private BlogService blogService;

    @GetMapping("/getUserBlogs")
    @ResponseBody
    public ResultEntity<List<SimpleBlogResponse>> getUserBlogs(Long userId) {
        return ResultEntity.success(blogService.getUserBlogsWithoutContent(userId));
    }

    @PostMapping("/saveBlog")
    @ResponseBody
    public ResultEntity<List<Blog>> saveBlog(Blog blog) {
        return ResultEntity.success(blogService.insert(blog));
    }

    @GetMapping("/getBlogDetail")
    public String getUserBlogs(Long id, Model model) {
        PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
        Blog b = blogService.getById(id);
        b.setContent(pdp.markdownToHtml(b.getContent()));
        model.addAttribute("blog", b);
        return "blog";
    }
}
