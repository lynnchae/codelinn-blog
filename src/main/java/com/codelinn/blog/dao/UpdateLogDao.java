package com.codelinn.blog.dao;

import com.codelinn.blog.model.UpdateLog;
import com.codelinn.blog.model.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.dao
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
