package org.lynn.springbootstarter.controller;

import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.controller.dto.BlogDto;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.service.BlogService;
import org.lynn.springbootstarter.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/12/1 15:49
 */
@Controller
@RequestMapping("/s")
public class SearchController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    @PostMapping("/blogs")
    @ResponseBody
    public ResultEntity<BlogDto> searchBlogs(String word) {
        List<Blog> list = blogService.searchUserBlogs(1L, word);
        List<BlogDto> blogs = new ArrayList<>();
        Comment c = new Comment();
        copyBlogAndCountComment(list, blogs, c, commentService);
        return ResultEntity.success(blogs);
    }

    static void copyBlogAndCountComment(List<Blog> list, List<BlogDto> blogs, Comment c, CommentService commentService) {
        for (Blog blog : list) {
            BlogDto bd = new BlogDto();
            BeanUtils.copyProperties(blog, bd);
            c.setBlogId(blog.getId());
            bd.setComments(commentService.count(c));
            blogs.add(bd);
        }
    }
}
