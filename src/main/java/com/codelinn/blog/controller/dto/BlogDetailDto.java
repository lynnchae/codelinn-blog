package com.codelinn.blog.controller.dto;

import com.codelinn.blog.model.Blog;
import lombok.Data;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.controller.dto
 * Description :
 *
 * @author : LiNn Cai
 * Date : 2019/11/11 7:15 下午
 */
@Data
public class BlogDetailDto extends Blog {

    private String toc;

    private String contentOrigin;

    List<BlogCommentsDto> comments;

}
