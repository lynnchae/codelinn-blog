package org.lynn.springbootstarter.service;

import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.model.vo.CommentVO;
import org.lynn.springbootstarter.service.base.BaseService;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:14 PM
 */
public interface CommentService extends BaseService<Comment> {

    List<CommentVO> queryCommentVO(Long blogId, Long parentId);

}
