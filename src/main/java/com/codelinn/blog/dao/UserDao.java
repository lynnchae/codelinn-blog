package com.codelinn.blog.dao;

import com.codelinn.blog.model.User;
import com.codelinn.blog.model.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Class Name : com.codelinn.blog.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 15:13
 */
@Mapper
@Repository
public interface UserDao extends BaseDao<User> {

    User getByUserId(Long userId);

}
