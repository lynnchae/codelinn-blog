package com.codelinn.blog.service;

import com.codelinn.blog.service.base.BaseService;
import com.codelinn.blog.model.Blog;
import com.codelinn.blog.model.base.Page;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:30
 */
public interface BlogService extends BaseService<Blog> {

    List<Blog> getUserBlogs(Long userId);

    List<Blog> searchUserBlogs(Long userId, String word);

    List<String> getTags();

    void updateLikes(Long id);

    void refreshTaglistCache();

    Page<Blog> getUserblogsPage(Long userId, Integer lastId, Integer pageSize);
}
