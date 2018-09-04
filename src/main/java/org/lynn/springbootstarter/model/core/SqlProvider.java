package org.lynn.springbootstarter.model.core;

import org.apache.ibatis.jdbc.SQL;
import org.lynn.springbootstarter.model.base.Entity;
import org.lynn.springbootstarter.model.util.ClazzUtils;

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
        WHERE(findParam,sql);
        return sql.toString();
    }

    private SQL SELECT_FROM(Class clazz) {
        final Map<String, Property> properties = ClazzUtils.getProperties(clazz, Operation.SELECT);
        return new SQL() {
            {
                for (Property p : properties.values()) {
                    SELECT(p.getName());
                }
                FROM(((Table) clazz.getAnnotation(Table.class)).name());
            }
        };
    }

    private SQL WHERE(Object findParam,SQL sql) {
        final Map<String, Property> properties = ClazzUtils.getPropertiesCondition(findParam, Operation.WHERE);
        for (Property property : properties.values()) {
            sql.WHERE(property.getName() + OPERATION_EQUALS + "#{" + property.getName() + "}");
        }
        return sql;
    }

}
