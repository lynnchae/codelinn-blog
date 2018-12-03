package org.lynn.springbootstarter.annotation;

import java.lang.annotation.*;

/**
 * Class Name : org.lynn.springbootstarter.annotation
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018-12-03 15:33
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FrequentShielded {
}
