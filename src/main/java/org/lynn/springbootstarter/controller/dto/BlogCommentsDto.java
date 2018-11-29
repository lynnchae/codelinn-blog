package org.lynn.springbootstarter.controller.dto;

import lombok.Data;
import org.lynn.springbootstarter.model.Comment;
import org.lynn.springbootstarter.model.vo.CommentVO;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.controller.dto
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
