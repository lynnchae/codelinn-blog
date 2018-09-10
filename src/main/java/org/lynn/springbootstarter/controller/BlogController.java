package org.lynn.springbootstarter.controller;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
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
    public ResultEntity<List<Blog>> getUserBlogs(Long userId) {
        return ResultEntity.success(blogService.getUserBlogsWithoutContent(userId));
    }

    @PostMapping("/saveBlog")
    @ResponseBody
    public ResultEntity<List<Blog>> saveBlog(Blog blog) {
        return ResultEntity.success(blogService.insert(blog));
    }

    @GetMapping("/getBlogDetail")
    public String getUserBlogs(Long id, Model model) {
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
        return "blog";
    }

    @PostMapping("/likeIt")
    @ResponseBody
    public String likeIt(Long id) {
        blogService.updateLikes(id);
        return "success";
    }
}
