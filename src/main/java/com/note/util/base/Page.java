package com.note.util.base;

import java.util.List;

/**
 * @className: Page
 * @description: 自定义page
 * @author: lgp
 * @date: 2020/12/5 15:13
 * @version: 1.0
 */
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {
    /**
     * 增加rows属性，不用定义属性，只需要get方法
     * @return
     */
    public List<T> getRows() {
        return super.getRecords();
    }

    /**
     * 把records设置为空
     * @return
     */
    @Override
    public List<T> getRecords() {
        return null;
    }

    /**
     * 有参构造方法
     * @param page
     * @param size
     */
    public Page (long page,long size) {
        super(page,size);
    }
}
