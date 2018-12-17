package com.codelinn.blog.model.core;

import com.codelinn.blog.model.util.ClazzUtils;
import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class Name : com.codelinn.blog.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 16:20
 */
public class Property {

    @Getter
    private String name;

    private String tableName;

    private Method readMethod;

    private Field field;

    @Getter
    private String columnName;

    public Property(Class clazz, PropertyDescriptor pd) {
        name = pd.getName();
        readMethod = pd.getReadMethod();
        tableName = ((Table) clazz.getAnnotation(Table.class)).name();
        columnName = getColumnName(pd.getName());
        setField(clazz, pd);
    }

    private String getColumnName(String name) {
        return name.replaceAll("([A-Z])", "_$1").toUpperCase();
    }

    private void setField(Class clazz, PropertyDescriptor pd) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(pd.getName());
                if (null != field) {
                    break;
                }
            } catch (NoSuchFieldException e) {

            }
        }
    }

    public boolean isTransient() {
        return ClazzUtils.hasAnnotation(readMethod, Transient.class) || ClazzUtils.hasAnnotation(field, Transient.class);
    }

    public boolean isId() {
        return ClazzUtils.hasAnnotation(readMethod, Id.class) || ClazzUtils.hasAnnotation(field, Id.class);
    }

    public boolean isNullValue(Object findParam) {
        try {
            return readMethod.invoke(findParam) == null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return true;

    }

}
