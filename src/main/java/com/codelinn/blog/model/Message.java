package com.codelinn.blog.model;

import com.codelinn.blog.model.base.Entity;
import lombok.Data;

import javax.persistence.Table;

/**
 * Class Name : com.codelinn.blog.model
 * Description : 留言表
 *
 * @author : cailinfeng
 * Date : 2018/11/12 12:28 PM
 */
@Data
@Table(name = "t_message")
public class Message extends Entity {

    private String sender;

    private String email;

    private String message;

}
