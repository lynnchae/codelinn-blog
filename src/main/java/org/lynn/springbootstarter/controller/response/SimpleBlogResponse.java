package org.lynn.springbootstarter.controller.response;

import lombok.Data;

import java.util.Date;

/**
 * Class Name : org.lynn.springbootstarter.controller.response
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 15:54
 */
@Data
public class SimpleBlogResponse {

    private Long id;

    private Long userId;

    private String title;

    private String tags;

    private Date createTime;

    private String content;

}
