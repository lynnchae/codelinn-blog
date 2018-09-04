package org.lynn.springbootstarter.model;

import lombok.Data;

import java.util.Date;

/**
 * Class Name : org.lynn.springbootstarter.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:19
 */
@Data
public class Entity {

    private Long id;
    private Date create_time;
    private String create_user;
    private Date update_time;
    private String update_user;

}
