package com.codelinn.blog.model;

import com.codelinn.blog.model.base.Entity;
import lombok.Data;

import javax.persistence.Table;

/**
 * Class Name : com.codelinn.blog.model.vo
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
