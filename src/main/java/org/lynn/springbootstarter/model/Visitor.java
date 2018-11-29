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
 * Date : 2018/11/29 22:05
 */
@Table(name = "t_visitor")
@Data
@ToString
public class Visitor extends Entity {

    private String name;

    private String email;

    private String avatar;

}
