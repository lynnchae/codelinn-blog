package org.lynn.springbootstarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private BlogService blogService;

    @RequestMapping("/")
    public String index(@RequestParam(required = false) String tag, Model model) {
        Blog b = new Blog();
        b.setUserId(1L);
        b.setTags(tag);
        List<Blog> list = blogService.query(b).stream().sorted( (o1,o2) -> o2.getId().compareTo(o1.getId())).collect(Collectors.toList());
        model.addAttribute("blogs", list);
        model.addAttribute("tags", blogService.getTags());
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
    public String blogeditor(Model model) {
        return "blogeditor";
    }

}
