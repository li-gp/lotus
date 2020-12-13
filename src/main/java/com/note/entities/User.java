package com.note.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: User
 * @description: 用户实体
 * @author: lgp
 * @date: 2020/11/15 16:26
 * @version: 1.0
 */
public class User implements Serializable {
    private int id;
    private String userName;
    private String name;
    private String passWord;
    private String checkPass;
    private String address;
    private String sex;
    private Date birthday;
    private String education;
    private String email;
    private String tel;
    private String nation;
    private Date createDate;
    private Date updateDate;

    public User(){
    }

    public User(int id, String userName, String name, String passWord, String checkPass, String address, String sex,
                Date birthday, String education, String email, String tel, String nation, Date createDate,
                Date updateDate) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.passWord = passWord;
        this.checkPass = checkPass;
        this.address = address;
        this.sex = sex;
        this.birthday = birthday;
        this.education = education;
        this.email = email;
        this.tel = tel;
        this.nation = nation;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(String checkPass) {
        this.checkPass = checkPass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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
