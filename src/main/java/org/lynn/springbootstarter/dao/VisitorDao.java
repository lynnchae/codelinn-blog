package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lynn.springbootstarter.model.Visitor;
import org.lynn.springbootstarter.model.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/29 22:05
 */
@Mapper
@Repository
public interface VisitorDao extends BaseDao<Visitor> {
}
