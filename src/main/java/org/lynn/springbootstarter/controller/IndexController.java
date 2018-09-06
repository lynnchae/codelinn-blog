package org.lynn.springbootstarter.controller;

import org.lynn.springbootstarter.controller.response.SimpleBlogResponse;
import org.lynn.springbootstarter.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/7 1:25
 */
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<SimpleBlogResponse> list = blogService.getUserBlogsWithoutContent(1L);
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

}
