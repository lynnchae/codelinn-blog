package com.codelinn.blog.service;

import com.codelinn.blog.service.base.BaseService;
import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.vo.CommentVO;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:14 PM
 */
public interface CommentService extends BaseService<Comment> {

    List<CommentVO> queryCommentVO(Long blogId, Long parentId);

}
