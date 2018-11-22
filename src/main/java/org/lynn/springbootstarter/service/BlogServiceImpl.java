package org.lynn.springbootstarter.service;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.dao.BlogDao;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.mybatis.TagUpdateEvent;
import org.lynn.springbootstarter.service.base.BaseServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:37
 */
@Service("blogService")
@Slf4j
public class BlogServiceImpl extends BaseServiceImpl<Blog> implements BlogService, ApplicationListener<TagUpdateEvent> {

    private static List<String> taglist = new ArrayList<>();

    @Resource
    private BlogDao blogDao;

    @Override
    public List<Blog> getUserBlogs(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        return blogDao.getUserBlogs(userId);
    }

    @Override
    public List<String> getTags() {
        if (taglist.size() == 0) {
            taglist = blogDao.getTags();
        }
        return taglist;
    }

    @Override
    public void updateLikes(Long id) {
        blogDao.updateLikes(id);
    }

    @Override
    public void onApplicationEvent(TagUpdateEvent applicationEvent) {
        refreshTaglistCache();
    }

    @Override
    public void refreshTaglistCache() {
        taglist = this.blogDao.getTags();
        if (log.isDebugEnabled()) {
            log.debug("taglist refreshed {}", taglist);
        }
    }
}
