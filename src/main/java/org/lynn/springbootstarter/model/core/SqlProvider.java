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

    private static String OPERATION_EQUALS = "=";

    public String get(T findParam) {
        Class modelClazz = findParam.getClass();
        SQL sql = SELECT_FROM(modelClazz);
        WHERE(findParam, sql);
        return sql.toString();
    }

    public String insert(T o) {
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
        Class modelClazz = o.getClass();
        SQL sql = new SQL() {
            {
                INSERT_INTO(((Table) modelClazz.getAnnotation(Table.class)).name());
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

    private SQL SELECT_FROM(Class clazz) {
        final Map<String, Property> properties = ClazzUtils.getProperties(clazz, Operation.SELECT);
        return new SQL() {
            {
                for (Property p : properties.values()) {
                    SELECT(p.getColumnName());
                }
                FROM(((Table) clazz.getAnnotation(Table.class)).name());
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
