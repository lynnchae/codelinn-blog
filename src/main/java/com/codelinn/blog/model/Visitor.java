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
 * Date : 2018/11/29 22:05
 */
@Table(name = "t_visitor")
@Data
@ToString
public class Visitor extends Entity {

    private Long visitorId;

    private String name;

    private String avatar;

}
