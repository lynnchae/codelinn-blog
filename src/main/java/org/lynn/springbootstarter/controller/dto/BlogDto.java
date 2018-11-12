package org.lynn.springbootstarter.controller.dto;

import lombok.Data;
import org.lynn.springbootstarter.model.Blog;

/**
 * Class Name : org.lynn.springbootstarter.controller.dto
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 22:12
 */
@Data
public class BlogDto extends Blog {

    private Integer comments;

}
