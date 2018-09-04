package org.lynn.springbootstarter.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.lynn.springbootstarter.model.base.Entity;
import org.lynn.springbootstarter.model.core.SqlProvider;
import org.springframework.stereotype.Repository;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:22
 */
@Repository
public interface BaseDao<T extends Entity> {

    @SelectProvider(type = SqlProvider.class,method = "get")
    T getByObject(T findParam);

//    T getById(Long id);
//
//    T count(T countParam);
//
//    T query(T findParam);
//
//    T insert(T object);
//
//    T update(T object);
//
//    T delete(T object);

}
