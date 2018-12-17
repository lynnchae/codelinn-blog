package com.codelinn.blog.dao;

import com.codelinn.blog.model.Metric;
import com.codelinn.blog.model.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Class Name : com.codelinn.blog.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/13 16:27 PM
 */
@Mapper
@Repository
public interface MetricDao extends BaseDao<Metric> {
}
