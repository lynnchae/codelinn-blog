package com.codelinn.blog.dao;

import com.codelinn.blog.controller.dto.BlogDto;
import com.codelinn.blog.model.Blog;
import com.codelinn.blog.model.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.dao
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

    List<BlogDto> getUserblogsPage(@Param("userId") Long userId, @Param("lastId") Integer lastId, @Param("pageSize") Integer pageSize);
}
