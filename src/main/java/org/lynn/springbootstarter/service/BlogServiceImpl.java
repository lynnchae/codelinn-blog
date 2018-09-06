package org.lynn.springbootstarter.service;

import org.lynn.springbootstarter.dao.BlogDao;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:37
 */
@Service
public class BlogServiceImpl extends BaseServiceImpl<Blog> implements BlogService{

    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Blog> getUserBlogs(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        Blog blog = new Blog();
        blog.setUserId(userId);
        return blogDao.query(blog);
    }
}
