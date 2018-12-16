package com.example.okay.shiyan;

/**
 * 用户类
 */
@SuppressWarnings("all")
public class User {
    private String name;
    private String pwd;
    private String sex;

    public User() {

    }

    public User(String uname, String pwd, String sex) {
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInfo() {
        return name + pwd + sex;
    }
}






