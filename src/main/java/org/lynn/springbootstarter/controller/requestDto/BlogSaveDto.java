package org.lynn.springbootstarter.controller.requestDto;

import lombok.Data;

/**
 * Class Name : org.lynn.springbootstarter.controller.requestDto
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:54
 */
@Data
public class BlogSaveDto {

    private Long userId;

    private String title;

    private String tags;

    private String content;

}
