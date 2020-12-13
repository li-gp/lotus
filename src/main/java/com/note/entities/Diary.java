package com.note.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @className: Diary
 * @description: 日记信息
 * @author: lgp
 * @date: 2020/12/4 10:29
 * @version: 1.0
 */
@Accessors(chain = true)
@TableName("diary_detail_info")
public class Diary {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private String title;
    private String weather;
    private String mood;
    private String especially;
    private String context;
    private Date createDate;
    private Date updateDate;

    public Diary () {

    }

    public Diary(Integer id, Integer uid, String title, String weather, String mood, String especially, String context, Date createDate, Date updateDate) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.weather = weather;
        this.mood = mood;
        this.especially = especially;
        this.context = context;
        this.createDate = createDate;
        this.updateDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getEspecially() {
        return especially;
    }

    public void setEspecially(String especially) {
        this.especially = especially;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
