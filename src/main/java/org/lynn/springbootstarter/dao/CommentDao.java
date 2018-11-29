package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.model.base.BaseDao;
import org.lynn.springbootstarter.model.vo.CommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:14 PM
 */
@Mapper
@Repository
public interface CommentDao extends BaseDao<Comment> {

    List<CommentVO> queryCommentVO(@Param("blogId") Long blogId,@Param("parentId") Long parentId);

}
