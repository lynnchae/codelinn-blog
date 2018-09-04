package org.lynn.springbootstarter.model;

import lombok.Data;
import lombok.ToString;
import org.lynn.springbootstarter.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : org.lynn.springbootstarter.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:11
 */
@Table(name = "t_user")
@Data
@ToString
public class User extends Entity {

    private Long user_id;

    private String name;

    private String sex;

    private String birthday;

}
