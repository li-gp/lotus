package com.note.util.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: ResultEnum
 * @description: 响应code枚举类
 * @author: lgp
 * @date: 2020/11/25 10:33
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(2000, "成功"),
    ERROR(999, "失败");

    private Integer code;
    private String desc;

}
