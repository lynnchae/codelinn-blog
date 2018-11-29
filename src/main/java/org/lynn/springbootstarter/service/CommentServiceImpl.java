package org.lynn.springbootstarter.service;

import org.lynn.springbootstarter.dao.CommentDao;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.model.vo.CommentVO;
import org.lynn.springbootstarter.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:15 PM
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public List<CommentVO> queryCommentVO(Long blogId, Long parentId) {
        return commentDao.queryCommentVO(blogId, parentId);
    }
}
