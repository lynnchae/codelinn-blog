package com.codelinn.blog.controller.dto;

import com.codelinn.blog.model.Blog;
import lombok.Data;

/**
 * Class Name : com.codelinn.blog.controller.dto
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 22:12
 */
@Data
public class BlogDto extends Blog {

    private Integer comments;

}
