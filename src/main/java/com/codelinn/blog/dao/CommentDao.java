package com.codelinn.blog.dao;

import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.base.BaseDao;
import com.codelinn.blog.model.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:14 PM
 */
@Mapper
@Repository
public interface CommentDao extends BaseDao<Comment> {

    List<CommentVO> queryCommentVO(@Param("blogId") Long blogId, @Param("parentId") Long parentId);

}
