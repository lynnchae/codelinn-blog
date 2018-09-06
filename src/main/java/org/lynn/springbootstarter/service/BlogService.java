package org.lynn.springbootstarter.service;

import org.lynn.springbootstarter.controller.response.SimpleBlogResponse;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.service.base.BaseService;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:30
 */
public interface BlogService extends BaseService<Blog> {

    List<SimpleBlogResponse> getUserBlogsWithoutContent(Long userId);

    List<String> getTags();

}
