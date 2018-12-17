package com.codelinn.blog.service.base;

import com.codelinn.blog.model.base.BaseDao;
import com.codelinn.blog.model.base.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Class Name : com.codelinn.blog.service
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/6 14:31
 */

public abstract class BaseServiceImpl<T extends Entity> implements BaseService<T> {

    @Autowired(required = false)
    private BaseDao<T> baseDao;

    @Override
    public T getByObject(T findParam) {
        return baseDao.getByObject(findParam);
    }

    @Override
    public T getById(Long id) {
        return baseDao.getById(id);
    }

    @Override
    public int count(T countParam) {
        return baseDao.count(countParam);
    }

    @Override
    public List<T> query(T findParam) {
        return baseDao.query(findParam);
    }

    @Override
    public int insert(T object) {
        return baseDao.insert(object);
    }

    @Override
    public int update(T object) {
        return baseDao.update(object);
    }

    @Override
    public void delete(Long id) {
        baseDao.delete(id);
    }
}
