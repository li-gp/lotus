package com.note.req;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: DiaryReq
 * @description: 日记查询请求类
 * @author: lgp
 * @date: 2020/12/5 9:56
 * @version: 1.0
 */
public class DiaryReq implements Serializable {
    private String title;
    private String especially;
    private Date createDate;
    private String mood;

    public DiaryReq() {

    }

    public DiaryReq(String title, String especially, Date createDate, String mood) {
        this.title = title;
        this.especially = especially;
        this.createDate = createDate;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEspecially() {
        return especially;
    }

    public void setEspecially(String especially) {
        this.especially = especially;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
