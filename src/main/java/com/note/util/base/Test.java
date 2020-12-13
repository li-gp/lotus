package com.note.util.base;

/**
 * @className: Test
 * @description: test
 * @author: lgp
 * @date: 2020/11/29 14:55
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        Result error; // = Result.error("用户名或密码错误");
        error = new Result(999,"用户名或密码错误","aaaa");
        System.out.println(error);
    }
}
