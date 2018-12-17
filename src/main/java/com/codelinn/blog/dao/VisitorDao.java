package com.codelinn.blog.dao;

import com.codelinn.blog.model.Visitor;
import com.codelinn.blog.model.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Class Name : com.codelinn.blog.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/29 22:05
 */
@Mapper
@Repository
public interface VisitorDao extends BaseDao<Visitor> {
}
