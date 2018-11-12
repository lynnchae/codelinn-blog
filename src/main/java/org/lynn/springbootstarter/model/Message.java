package org.lynn.springbootstarter.model;

import lombok.Data;
import org.lynn.springbootstarter.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : org.lynn.springbootstarter.model
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
