package org.lynn.springbootstarter.service;

import lombok.extern.slf4j.Slf4j;
import org.lynn.springbootstarter.dao.BlogDao;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.model.base.Page;
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
        return blogDao.searchUserBlogs(userId, null);
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

    @Override
    public List<Blog> searchUserBlogs(Long userId, String word) {
        return blogDao.searchUserBlogs(userId, word);
    }

    @Override
    public Page<Blog> getUserblogsPage(Long userId, Integer lastId, Integer pageSize) {
        Page<Blog> blogPage = new Page<>();
        //用户博客最大id
        Integer maxId = blogDao.getMaxUserblogid(userId);

        //为了处理id不连续的情况
        Integer processedId;
        if (lastId == null || lastId <= 0 || lastId == maxId) {
            lastId = maxId;
            processedId = maxId;
        } else {
            processedId = lastId - 1;
        }
        //id顺序的前面page+1个id，为了判断是不是最后一页
        List<Long> ids = blogDao.getLastPageIds(userId, pageSize + 1);
        blogPage.setLastPage(false);
        if(lastId!=null && ids.contains(Long.valueOf(lastId))){
            blogPage.setLastPage(true);
        }
        List<Blog> list = blogDao.getUserblogsPage(userId, processedId, pageSize);
        blogPage.setPages(pageSize);
        Blog blog = new Blog();
        blog.setUserId(userId);
        Integer userTotalBlogs = count(blog);
        blogPage.setTotal(userTotalBlogs);
        if (userTotalBlogs % pageSize == 0) {
            blogPage.setPages(userTotalBlogs / pageSize);
        } else {
            blogPage.setPages((userTotalBlogs / pageSize) + 1);
        }
        blogPage.setList(list);
        return blogPage;
    }
}
