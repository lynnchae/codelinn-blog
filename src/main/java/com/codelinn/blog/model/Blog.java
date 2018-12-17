package com.codelinn.blog.model;

import lombok.Data;
import com.codelinn.blog.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : com.codelinn.blog.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:26
 */
@Data
@Table(name = "t_blog")
public class Blog extends Entity {

    private Long userId;

    private String title;

    private String tags;

    private String content;

    private Integer likes;

    private Integer done;

}
