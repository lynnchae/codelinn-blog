package org.lynn.springbootstarter.controller;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.controller.dto.BlogCommentsDto;
import org.lynn.springbootstarter.controller.dto.BlogDto;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.service.BlogService;
import org.lynn.springbootstarter.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @Resource
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/getUserBlogs")
    @ResponseBody
    public ResultEntity<List<Blog>> getUserBlogs(Long userId) {
        return ResultEntity.success(blogService.getUserBlogs(userId));
    }

    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    public String saveBlog(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(name = "blog") Blog blog, Model model) throws IOException {
        String password = request.getParameter("password");
        if (!"lynnchae".equals(password)) {
            response.sendError(500, "Please enter the wright password to submit the article!");
            return "blogeditor";
        } else {
            if(blog.getId() != null) {
                blogService.update(blog);
            }else{
                blog.setUserId(1L);
                blogService.insert(blog);
            }
            return "index";
        }

    }

    @RequestMapping("/{id}/b")
    public String getUserBlogs(@PathVariable Long id, Model model) {
        Blog b = blogService.getById(id);
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[]{TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(b.getContent());
        String html = renderer.render(document);
        b.setContent(html);
        /**
         * pegdown模式
         * PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
         * b.setContent(pdp.markdownToHtml(b.getContent()));
         */
        model.addAttribute("blog", b);
        Comment comment = new Comment();
        comment.setBlogId(id);
        comment.setParentId(0L);
        List<Comment> comments = commentService.query(comment);
        List<BlogCommentsDto> bcomments = new ArrayList<>();
        for (Comment c : comments) {
            BlogCommentsDto bc = new BlogCommentsDto();
            BeanUtils.copyProperties(c, bc);
            comment.setBlogId(null);
            comment.setParentId(bc.getId());
            bc.setComments(commentService.query(comment));
            bcomments.add(bc);
        }
        model.addAttribute("comments", bcomments);
        return "blog";
    }

    @PostMapping("/likeIt")
    @ResponseBody
    public String likeIt(Long id) {
        blogService.updateLikes(id);
        return "success";
    }

    @PostMapping("/tagIt")
    @ResponseBody
    public List<BlogDto> tagIt(String tag) {
        Blog b = new Blog();
        b.setUserId(1L);
        b.setTags(tag);
        List<Blog> blogs = blogService.query(b);
        BlogDto bdto;
        List<BlogDto> resultList = new ArrayList<>();
        Comment c = new Comment();
        for (Blog bl : blogs) {
            bdto = new BlogDto();
            BeanUtils.copyProperties(bl, bdto);
            c.setBlogId(bl.getId());
            bdto.setComments(commentService.count(c));
            resultList.add(bdto);
        }
        Collections.reverse(resultList);
        return resultList;
    }
}
