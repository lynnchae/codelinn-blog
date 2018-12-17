package com.codelinn.blog.model.base;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * Class Name : com.codelinn.blog.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:19
 */
@Data
public class Entity {

    @Id
    private Long id;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}
