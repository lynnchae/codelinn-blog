package com.codelinn.blog.controller;

import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.controller.dto.BlogDetailDto;
import com.codelinn.blog.model.Blog;
import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.User;
import com.codelinn.blog.model.base.Page;
import com.codelinn.blog.model.vo.CommentVO;
import com.codelinn.blog.service.BlogService;
import com.codelinn.blog.service.CommentService;
import com.codelinn.blog.service.UserService;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.emoji.EmojiImageType;
import com.vladsch.flexmark.ext.emoji.EmojiShortcutType;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.codelinn.blog.controller.dto.BlogCommentsDto;
import com.codelinn.blog.controller.dto.BlogDto;
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

import static cn.hutool.core.util.StrUtil.isBlank;

/**
 * Class Name : com.codelinn.blog.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:51
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    private static HtmlRenderer renderer;

    private static Parser parser;

    private static String toc_option = "[TOC levels=1-4,html] \n";

    static {
        MutableDataSet options = new MutableDataSet()
                .set(Parser.LISTS_ORDERED_LIST_MANUAL_START, true)
                .set(Parser.EXTENSIONS, Arrays.asList(
                        AutolinkExtension.create(),
                        EmojiExtension.create(),
                        StrikethroughExtension.create(),
                        TaskListExtension.create(),
                        TablesExtension.create(),
                        TocExtension.create()))
                .set(TablesExtension.WITH_CAPTION, false)
                .set(TablesExtension.COLUMN_SPANS, false)
                .set(TablesExtension.MIN_HEADER_ROWS, 1)
                .set(TablesExtension.MAX_HEADER_ROWS, 1)
                .set(TablesExtension.APPEND_MISSING_COLUMNS, true)
                .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true)
                .set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true)
                .set(EmojiExtension.USE_SHORTCUT_TYPE, EmojiShortcutType.GITHUB)
                .set(EmojiExtension.USE_IMAGE_TYPE, EmojiImageType.IMAGE_ONLY)
                .set(TocExtension.LEVELS, 4)
                .set(TocExtension.IS_TEXT_ONLY, true)
                .set(TocExtension.LIST_CLASS, "ul-class");
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    @Resource
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserBlogs")
    @ResponseBody
    public ResultEntity<Page<BlogDto>> getUserBlogs(@RequestParam(required = false) Long userId,
                                                    @RequestParam(required = false) String tag,
                                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page blogPage = blogService.getUserblogsPage(userId, null, pageSize);
        List<BlogDto> blogs = new ArrayList<>();
        Comment c = new Comment();
        SearchController.copyBlogAndCountComment(blogPage.getList(), blogs, c, commentService);
        blogPage.setList(blogs);
        return ResultEntity.success(blogPage);
    }

    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    public String saveBlog(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(name = "blog") Blog blog, Model model) throws IOException {
        String password = request.getParameter("password");
        if (!"lynnchae".equals(password)) {
            response.sendError(500, "Please enter the wright password to submit the article!");
            return "blogeditor";
        } else {
            if (blog.getId() != null) {
                blogService.update(blog);
            } else {
                blog.setUserId(1L);
                blogService.insert(blog);
            }
            return "index";
        }

    }

    @RequestMapping(value = "/sBlog", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResultEntity<Boolean> saveBlog(HttpServletRequest request, @RequestBody Blog blog) {
        String token = request.getHeader("token");
        if(isBlank(token)){
            return ResultEntity.error(400,"permission denied!");
        }
        User user = new User();
        user.setToken(token);
        if(userService.count(user) == 0){
            return ResultEntity.error(400,"unknown user!");
        }
        user = userService.getByObject(user);
        if(isBlank(blog.getContent()) || isBlank(blog.getTitle()) || isBlank(blog.getTags())){
            return ResultEntity.error(400,"blog incomplete!");
        }
        blog.setUserId(user.getUserId());
        if (blog.getId() != null) {
            if(user.getUserId() != blog.getUserId()){
                return ResultEntity.error(400,"not your blog!");
            }
            blogService.update(blog);
        } else {
            blogService.insert(blog);
        }
        return ResultEntity.success(true);
    }


    @RequestMapping("/{id}/b")
    public String getUserBlogs(@PathVariable Long id, Model model) {
        Blog b = blogService.getById(id);
        StringBuffer tocedContent = new StringBuffer(toc_option).append(b.getContent());
        Node tocDocument = parser.parse(tocedContent.toString());
        String tocHtml = renderer.render(tocDocument);
        Document doc = Jsoup.parse(tocHtml);
        Elements toc = doc.select("ul.ul-class");
        Node document = parser.parse(b.getContent());
        String html = renderer.render(document);
        b.setContent(html);
        model.addAttribute("blog", b);
        List<CommentVO> comments = commentService.queryCommentVO(id, 0L);
        List<BlogCommentsDto> bcomments = new ArrayList<>();
        for (CommentVO c : comments) {
            BlogCommentsDto bc = new BlogCommentsDto();
            BeanUtils.copyProperties(c, bc);
            bc.setComments(commentService.queryCommentVO(null, bc.getId()));
            bcomments.add(bc);
        }
        model.addAttribute("comments", bcomments);
        model.addAttribute("toc", toc.toString());
        return "blog";
    }

    @RequestMapping("/gBlog")
    @ResponseBody
    public BlogDetailDto getBlogById(@RequestParam Long id) {
        Blog b = blogService.getById(id);
        String originContent = b.getContent();
        BlogDetailDto blogTarget = new BlogDetailDto();
        StringBuffer tocedContent = new StringBuffer(toc_option).append(b.getContent());
        Node tocDocument = parser.parse(tocedContent.toString());
        String tocHtml = renderer.render(tocDocument);
        Document doc = Jsoup.parse(tocHtml);
        Elements toc = doc.select("ul.ul-class");
        Node document = parser.parse(b.getContent());
        String html = renderer.render(document);
        BeanUtils.copyProperties(b, blogTarget);
        blogTarget.setContent(html);
        List<CommentVO> comments = commentService.queryCommentVO(id, 0L);
        List<BlogCommentsDto> bcomments = new ArrayList<>();
        for (CommentVO c : comments) {
            BlogCommentsDto bc = new BlogCommentsDto();
            BeanUtils.copyProperties(c, bc);
            bc.setComments(commentService.queryCommentVO(null, bc.getId()));
            bcomments.add(bc);
        }
        blogTarget.setComments(bcomments);
        blogTarget.setToc(toc.toString());
        blogTarget.setContentOrigin(originContent);
        return blogTarget;
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
        List<BlogDto> resultList = new ArrayList<>();
        Comment c = new Comment();
        SearchController.copyBlogAndCountComment(blogs, resultList, c, commentService);
        Collections.reverse(resultList);
        return resultList;
    }

    @PostMapping("/loadMore")
    @ResponseBody
    public Page<BlogDto> loadMore(@RequestParam(required = false) Integer lastId,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page blogs = blogService.getUserblogsPage(1L, lastId, pageSize);
        List<BlogDto> resultList = new ArrayList<>();
        Comment c = new Comment();
        SearchController.copyBlogAndCountComment(blogs.getList(), resultList, c, commentService);
        blogs.setList(resultList);
        return blogs;
    }
}
