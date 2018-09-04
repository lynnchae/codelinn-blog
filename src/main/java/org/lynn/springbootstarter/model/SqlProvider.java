package org.lynn.springbootstarter.model;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.cglib.core.ReflectUtils;

import javax.persistence.Table;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Class Name : org.lynn.springbootstarter.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 14:21
 */
public class SqlProvider<T extends Entity> {

    public String get(T findParam){
        Class modelClazz = findParam.getClass();
        Table table = (Table) modelClazz.getAnnotation(Table.class);
        SQL sql = new SQL();
        Field[] fields = modelClazz.getDeclaredFields();
        for(Class<?> clazz = modelClazz ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            for(Field f : clazz.getDeclaredFields()){
                sql.SELECT(f.getName());
            }
        }
        sql.FROM(table.name());
        PropertyDescriptor[] pds = ReflectUtils.getBeanGetters(modelClazz);
        for(PropertyDescriptor pd : pds){
            try {
                if(pd.getReadMethod().invoke(findParam) != null ){
                    sql.WHERE(pd.getName().concat("=").concat("#{").concat(pd.getName()).concat("}"));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return sql.toString();
    }

}
