package org.lynn.springbootstarter.model;

import lombok.Data;
import org.lynn.springbootstarter.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : org.lynn.springbootstarter.model
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

}
