package org.lynn.springbootstarter.model.core;

import org.apache.ibatis.jdbc.SQL;
import org.joda.time.DateTime;
import org.lynn.springbootstarter.model.base.Entity;
import org.lynn.springbootstarter.model.util.ClazzUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Table;
import java.util.Map;

/**
 * Class Name : org.lynn.springbootstarter.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:21
 */
public class SqlProvider<T extends Entity> {

    private String tableName;

    private Class<?> modelClazz;

    private static ThreadLocal<Class<?>> modelClazzHolder = new ThreadLocal<>();

    private static String OPERATION_EQUALS = "=";

    private void init() {
        modelClazz = modelClazzHolder.get();
        tableName = modelClazz.getAnnotation(Table.class).name();
    }

    public static void setModelClazz(Class clazz){
        modelClazzHolder.set(clazz);
    }

    public static void removeModelClazz(){
        modelClazzHolder.remove();
    }

    public String get(T findParam) {
        init();
        SQL sql = SELECT_FROM();
        WHERE(findParam, sql);
        return sql.toString();
    }

    public String insert(T o) {
        init();
        if (o.getCreateTime() == null) {
            DateTime now = DateTime.now();
            o.setCreateTime(now.toDate());
            o.setUpdateTime(now.toDate());
        }

        if (StringUtils.isEmpty(o.getCreateUser())) {
            o.setCreateUser("system");
        }
        if (StringUtils.isEmpty(o.getUpdateUser())) {
            o.setUpdateUser("system");
        }
        SQL sql = new SQL() {
            {
                INSERT_INTO(tableName);
                Map<String, Property> propertyMap = ClazzUtils.getProperties(modelClazz, Operation.INSERT);
                for (Property p : propertyMap.values()) {
                    if (p.isId() || p.isNullValue(o)) {
                        continue;
                    }
                    VALUES(p.getColumnName(), "#{" + p.getName() + "}");
                }
            }
        };
        return sql.toString();
    }

    public String getById(Long id){
        init();
        return SELECT_FROM().WHERE("ID = #{id}").toString();
    }

    private SQL SELECT_FROM() {
        final Map<String, Property> properties = ClazzUtils.getProperties(modelClazz, Operation.SELECT);
        return new SQL() {
            {
                for (Property p : properties.values()) {
                    SELECT(p.getColumnName());
                }
                FROM(tableName);
            }
        };
    }

    private SQL WHERE(Object findParam, SQL sql) {
        final Map<String, Property> properties = ClazzUtils.getPropertiesCondition(findParam, Operation.WHERE);
        for (Property property : properties.values()) {
            sql.WHERE(property.getColumnName() + OPERATION_EQUALS + "#{" + property.getName() + "}");
        }
        return sql;
    }

}
