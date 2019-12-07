package com.codelinn.blog.controller;

import com.codelinn.blog.annotation.FrequentShielded;
import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.model.Blog;
import com.codelinn.blog.model.Comment;
import com.codelinn.blog.service.BlogService;
import com.codelinn.blog.service.CommentService;
import com.codelinn.blog.controller.dto.BlogDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Name : com.codelinn.blog.controller
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
    @FrequentShielded
    public ResultEntity<BlogDto> searchBlogs(String word) {
        List<Blog> list = blogService.searchUserBlogs(null, word);
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
