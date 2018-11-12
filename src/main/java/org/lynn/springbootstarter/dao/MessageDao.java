package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lynn.springbootstarter.model.Message;
import org.lynn.springbootstarter.model.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 12:30 PM
 */
@Mapper
@Repository
public interface MessageDao extends BaseDao<Message> {
}
