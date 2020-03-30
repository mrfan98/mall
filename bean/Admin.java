/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 2:43 下午
 */
package com.cskaoyan.mall.bean;

public class Admin {

    private Integer id;

    private String email;

    private String nickname;

    private String pwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
