package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lynn.springbootstarter.model.Blog;
import org.lynn.springbootstarter.model.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:29
 */
@Mapper
@Repository
public interface BlogDao extends BaseDao<Blog> {

    List<Blog> getUserBlogsWithoutContent(Long userId);

    List<String> getTags();

    void updateLikes(Long id);
}
