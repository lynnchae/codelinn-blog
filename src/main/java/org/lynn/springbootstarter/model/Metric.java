package org.lynn.springbootstarter.model;

import lombok.Data;
import org.lynn.springbootstarter.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : org.lynn.springbootstarter.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/13 4:24 PM
 */
@Data
@Table(name = "t_metric")
public class Metric extends Entity {

    //统计字段
    private String col;

    //指标值
    private String quota;

    //统计类型
    private String type;

    //统计日期
    private String date;

}
