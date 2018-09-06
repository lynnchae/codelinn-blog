package org.lynn.springbootstarter.model.base;

import org.apache.ibatis.annotations.*;
import org.lynn.springbootstarter.model.core.SqlProvider;

import java.util.List;

/**
 * Class Name : org.lynn.springbootstarter.dao
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:22
 */
public interface BaseDao<T extends Entity> {

    @SelectProvider(type = SqlProvider.class, method = "get")
    @ResultMap(value = "resultMap")
    T getByObject(T findParam);

    @SelectProvider(type = SqlProvider.class, method = "getById")
    @ResultMap(value = "resultMap")
    T getById(Long id);

    @SelectProvider(type = SqlProvider.class, method = "count")
    int count(T countParam);

    @SelectProvider(type = SqlProvider.class, method = "get")
    @ResultMap(value = "resultMap")
    List<T> query(T findParam);

    @InsertProvider(type = SqlProvider.class, method = "insert")
    int insert(T object);

    @UpdateProvider(type = SqlProvider.class, method = "update")
    T update(T object);

    @DeleteProvider(type = SqlProvider.class, method = "delete")
    void delete(Long id);

}
