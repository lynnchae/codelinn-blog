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
        if (o.getCreate_time() == null) {
            DateTime now = DateTime.now();
            o.setCreate_time(now.toDate());
            o.setUpdate_time(now.toDate());
        }

        if (StringUtils.isEmpty(o.getCreate_user())) {
            o.setCreate_user("system");
        }
        if (StringUtils.isEmpty(o.getUpdate_user())) {
            o.setUpdate_user("system");
        }
        Class modelClazz = o.getClass();
        SQL sql = new SQL(){
            {
                INSERT_INTO(((Table) modelClazz.getAnnotation(Table.class)).name());
                Map<String,Property> propertyMap = ClazzUtils.getProperties(modelClazz,Operation.INSERT);
                for(Property p : propertyMap.values()){
                    if(p.isId() || p.isNullValue(o)){
                        continue;
                    }
                    VALUES(p.getName(),"#{" + p.getName() + "}");
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
                    SELECT(p.getName());
                }
                FROM(((Table) clazz.getAnnotation(Table.class)).name());
            }
        };
    }

    private SQL WHERE(Object findParam, SQL sql) {
        final Map<String, Property> properties = ClazzUtils.getPropertiesCondition(findParam, Operation.WHERE);
        for (Property property : properties.values()) {
            sql.WHERE(property.getName() + OPERATION_EQUALS + "#{" + property.getName() + "}");
        }
        return sql;
    }

}
