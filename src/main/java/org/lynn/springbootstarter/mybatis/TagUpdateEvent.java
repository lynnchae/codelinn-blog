package org.lynn.springbootstarter.mybatis;

import org.springframework.context.ApplicationEvent;

/**
 * Class Name : org.lynn.springbootstarter.mybatis
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/20 4:38 PM
 */
public class TagUpdateEvent extends ApplicationEvent {

    public TagUpdateEvent(Object source) {
        super(source);
    }
}
