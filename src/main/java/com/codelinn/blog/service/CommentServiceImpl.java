package com.codelinn.blog.service;

import com.codelinn.blog.service.base.BaseServiceImpl;
import com.codelinn.blog.dao.CommentDao;
import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.vo.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class Name : com.codelinn.blog.service
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
