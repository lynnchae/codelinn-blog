package com.codelinn.blog.model;

import com.codelinn.blog.model.base.Entity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Class Name : com.codelinn.blog.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:11
 */
@Table(name = "t_user")
@Data
@ToString
public class User extends Entity {

    private Long userId;

    private String name;

    private String sex;

    private String birthday;

    private String email;

    private String avatarUrl;

    private String githubToken;

}
