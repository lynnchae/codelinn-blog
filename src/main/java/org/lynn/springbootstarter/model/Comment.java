package org.lynn.springbootstarter.model;

import lombok.Data;
import org.lynn.springbootstarter.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : org.lynn.springbootstarter.model
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

    private Integer blogId;

    private String comment;

    private Integer parentId;

    private String replyTo;

}
