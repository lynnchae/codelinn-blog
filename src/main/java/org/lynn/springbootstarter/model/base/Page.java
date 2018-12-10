package org.lynn.springbootstarter.model.base;

import lombok.Data;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.model.base
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018-12-10 13:36
 */
@Data
public class Page<T>{
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer pages;

    private Boolean lastPage;

    private Long lastId;

    private List<T> list;

}
