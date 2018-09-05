package org.lynn.springbootstarter.common;

import lombok.Data;

/**
 * Class Name : org.lynn.springbootstarter.common
 * Description :公用返回结果
 *
 * @author : cailinfeng
 * Date : 2018/9/5 15:36
 */
@Data
public class ResultEntity<T> {

    private T data;

    /**
     * 1 成功
     */
    private Integer code;

    private String message;

    public static ResultEntity success(Object result){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(1);
        resultEntity.setData(result);
        resultEntity.setMessage("success!");
        return resultEntity;
    }

    public static ResultEntity error(Integer code,String message){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCode(code);
        resultEntity.setMessage(message);
        return resultEntity;
    }

}
