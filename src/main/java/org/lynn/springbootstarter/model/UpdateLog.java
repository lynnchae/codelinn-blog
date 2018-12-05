package org.lynn.springbootstarter.model;

import lombok.Data;
import org.lynn.springbootstarter.model.base.Entity;

import javax.persistence.Table;

/**
 * Class Name : org.lynn.springbootstarter.model.vo
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018-12-05 16:41
 */
@Table(name="t_update_log")
@Data
public class UpdateLog extends Entity {

    private String detail;

}
