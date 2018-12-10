package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    Integer getMaxUserblogid(@Param("userId") Long userId);

    List<Long> getLastPageIds(@Param("userId") Long userId, @Param("pageSize") Integer pageSize);

    List<String> getTags();

    void updateLikes(Long id);

    List<Blog> searchUserBlogs(@Param("userId") Long userId, @Param("word") String word);

    List<Blog> getUserblogsPage(@Param("userId") Long userId, @Param("lastId") Integer lastId, @Param("pageSize") Integer pageSize);
}
