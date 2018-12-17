package com.codelinn.blog.dao;

import com.codelinn.blog.model.Message;
import com.codelinn.blog.model.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Class Name : com.codelinn.blog.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 12:30 PM
 */
@Mapper
@Repository
public interface MessageDao extends BaseDao<Message> {
}
