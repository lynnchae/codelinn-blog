package org.lynn.springbootstarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.controller.dto.BlogDto;
import org.lynn.springbootstarter.dao.UpdateLogDao;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.model.base.Page;
import org.lynn.springbootstarter.service.BlogService;
import org.lynn.springbootstarter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 16:46
 */
@Controller
@Slf4j
public class IndexController {

    private static List<String> rollingPicUlrs;

    static {
        rollingPicUlrs = new ArrayList<>();
        String url7 = "https://ws2.sinaimg.cn/large/006tNbRwgy1fxvnizat0bj32080go171.jpg";
        String url6 = "https://ws3.sinaimg.cn/large/006tNbRwgy1fxvnlg1j2xj32080godso.jpg";
        String url1 = "https://ws2.sinaimg.cn/large/006tNbRwgy1fxvr0yh71dj32080jg7kf.jpg";
        String url2 = "https://ws2.sinaimg.cn/large/006tNbRwgy1fxvr1iskezj32080gowlr.jpg";
        String url3 = "https://ws1.sinaimg.cn/large/006tNbRwgy1fxvr2bhn8hj32080goam8.jpg";
        String url4 = "https://ws2.sinaimg.cn/large/006tNbRwgy1fxvr2j61kxj32080goapd.jpg";
        String url5 = "https://ws1.sinaimg.cn/large/006tNbRwgy1fxvr2uw6rqj32080goq6x.jpg";
        String url8 = "https://ws1.sinaimg.cn/large/006tNbRwgy1fxvr3297xrj32080godp5.jpg";
        String url9 = "https://ws1.sinaimg.cn/large/006tNbRwgy1fxvtxqtaxwj32080go1e2.jpg";
        String url10 = "https://ws3.sinaimg.cn/large/006tNbRwgy1fxvu3di59oj32080go7h9.jpg";
        String url12 = "https://ws3.sinaimg.cn/large/006tNbRwgy1fxvu9npm7gj32080go19s.jpg";
        rollingPicUlrs.add(url1);
        rollingPicUlrs.add(url2);
        rollingPicUlrs.add(url3);
        rollingPicUlrs.add(url4);
        rollingPicUlrs.add(url5);
        rollingPicUlrs.add(url6);
        rollingPicUlrs.add(url7);
        rollingPicUlrs.add(url8);
        rollingPicUlrs.add(url9);
        rollingPicUlrs.add(url10);
        rollingPicUlrs.add(url12);
    }

    @Resource
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Resource
    private UpdateLogDao updateLogDao;

    @RequestMapping("/")
    public String index(@RequestParam(required = false) String tag,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        Blog b = new Blog();
        b.setUserId(1L);
        b.setTags(tag);
        Page blogPage = blogService.getUserblogsPage(1L, null, pageSize);
        List<BlogDto> blogs = new ArrayList<>();
        Comment c = new Comment();
        SearchController.copyBlogAndCountComment(blogPage.getList(), blogs, c, commentService);
        blogPage.setList(blogs);
        model.addAttribute("blogs", blogPage);
        model.addAttribute("tags", blogService.getTags());
        Integer fileNameIndex1 = new Random().nextInt(rollingPicUlrs.size());
        model.addAttribute("pic1", rollingPicUlrs.get(fileNameIndex1));
        Integer fileNameIndex2 = new Random().nextInt(rollingPicUlrs.size());
        while (fileNameIndex1 == fileNameIndex2) {
            fileNameIndex2 = new Random().nextInt(rollingPicUlrs.size());
        }
        model.addAttribute("pic2", rollingPicUlrs.get(fileNameIndex2));
        model.addAttribute("updatelogs", updateLogDao.getRecent10Updatelog());
        return "index";
    }

    @RequestMapping("/aboutMe")
    public String aboutMe(Model model) {
        return "about-me";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @RequestMapping("/blogeditor")
    public String blogeditor(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("blog", blogService.getById(id));
        }
        return "blogeditor";
    }

}
