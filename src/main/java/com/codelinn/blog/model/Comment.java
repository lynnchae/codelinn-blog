package com.codelinn.blog.model;

import com.codelinn.blog.model.base.Entity;
import lombok.Data;

import javax.persistence.Table;

/**
 * Class Name : com.codelinn.blog.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 6:10 PM
 */
@Data
@Table(name = "t_comment")
public class Comment extends Entity {

    private String commenter;

    private String commenterEmail;

    private Long blogId;

    private String comment;

    private Long parentId;

    private String replyTo;

    private Long visitorId;

}
