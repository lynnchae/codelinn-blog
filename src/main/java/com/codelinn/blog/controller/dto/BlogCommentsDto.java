package com.codelinn.blog.controller.dto;

import com.codelinn.blog.model.Comment;
import com.codelinn.blog.model.vo.CommentVO;
import lombok.Data;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.controller.dto
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 21:27
 */
@Data
public class BlogCommentsDto extends Comment {

    private String avatar;

    List<CommentVO> comments;

}
