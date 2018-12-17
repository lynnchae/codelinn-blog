package com.codelinn.blog.model.util;

import com.codelinn.blog.model.core.Operation;
import com.codelinn.blog.model.core.Property;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Name : com.codelinn.blog.model
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/9/4 16:20
 */
public class ClazzUtils {

    public static Map<String, Property> getProperties(Class clazz, Operation operation) {
        PropertyDescriptor[] pds = ReflectUtils.getBeanGetters(clazz);
        Map<String, Property> map = new HashMap<>(pds.length);
        for (PropertyDescriptor pd : pds) {
            Property p = new Property(clazz, pd);
            if (p.isTransient()) {
                continue;
            }
            if (operation == Operation.INSERT ||
                    operation == Operation.UPDATE ||
                        operation == Operation.WHERE) {
                if (p.isId()) {
                    continue;
                }
            }
            map.put(p.getName(), p);
        }
        return map;
    }

    public static Map<String, Property> getPropertiesCondition(Object findParam, Operation operation) {
        Map<String, Property> map = getProperties(findParam.getClass(),operation);
        Map<String, Property> results = new HashMap<String, Property>(map.size());
        for (Map.Entry<String, Property> propertyEntry : map.entrySet()) {
            Property property = propertyEntry.getValue();
            if (operation == Operation.INSERT || operation == Operation.UPDATE || operation == Operation.WHERE) {
                // 空值忽略
                if (property.isNullValue(findParam)) {
                    continue;
                }
            }
            results.put(propertyEntry.getKey(), property);
        }
        return results;
    }

    public static Boolean hasAnnotation(AccessibleObject accessibleObject, Class<? extends Annotation> annotationClass) {
        return getAnnotation(accessibleObject, annotationClass) != null;
    }

    public static Annotation getAnnotation(AccessibleObject accessibleObject, Class<? extends Annotation> annotationClass) {
        if (accessibleObject == null) {
            return null;
        }
        return accessibleObject.getAnnotation(annotationClass);
    }

}
