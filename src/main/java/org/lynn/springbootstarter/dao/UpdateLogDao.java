package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lynn.springbootstarter.model.UpdateLog;
import org.lynn.springbootstarter.model.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018-12-05 16:44
 */
@Mapper
@Repository
public interface UpdateLogDao extends BaseDao<UpdateLog> {

    public List<UpdateLog> getRecent10Updatelog();

}
