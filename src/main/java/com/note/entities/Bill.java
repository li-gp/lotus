package com.note.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Bill
 * @Description: 账单类
 * @Author: lgp
 * @Date: 2020/9/24 18:20
 * @Version: 1.0
 */
public class Bill {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Date createDate;
    private Date updateDate;
    private Integer userId;
    private String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
