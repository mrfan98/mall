/**
 * User: zsquirrel
 * Date: 2020/4/1
 * Time: 10:38 上午
 */
package com.cskaoyan.mall.bean;

public class User {

    private String nickname;

    private String name;

    /**
     * 只允许有一个address，更换address
     */
    private String address;

    private String phone;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
