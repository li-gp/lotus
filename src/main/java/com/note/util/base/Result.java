package com.note.util.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @className: Result
 * @description: 封装统一的响应结果
 * @author: lgp
 * @date: 2020/11/25 10:28
 * @version: 1.0
 */
@Data
public class Result implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    //响应业务状态吗
    private Integer code;

    // 是否正常
    private Boolean flag;

    // 响应信息
    private String message;

    // 响应数据
    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        // Integer比较大小equals 默认-128-127之间等号是true，超过范围false，等号比较地址，
        // 超范围new Integer(i)地址改变了。
        this.flag = code.equals(ResultEnum.SUCCESS.getCode());
        this.message = message;
        this.data = data;
    }

    public static Result ok(){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getDesc(),null);
    }

    public static Result ok(Object data){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getDesc(),data);
    }

    public static Result ok(String message, Object data){
        return new Result(ResultEnum.SUCCESS.getCode(),message,data);
    }

    public static Result error(String message){
        logger.info("返回错误：code={},message={}",ResultEnum.ERROR.getCode(),message);
        return new Result(ResultEnum.ERROR.getCode(),message,null);
    }

    public static Result build(int code, String message){
        logger.debug("返回结果：code={},message={}",code,message);
        return new Result(code,message,null);
    }

    public static Result build(ResultEnum resultEnum){
        logger.debug("返回结果：code={},message={}",resultEnum.getCode(),resultEnum.getDesc());
        return new Result(resultEnum.getCode(),resultEnum.getDesc(),null);
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }

}
