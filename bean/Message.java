package com.cskaoyan.mall.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by 张凡 on 2020/4/1 18:03
 */

public class Message {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String content;
    private String replyCont;
    private Integer state;
    private String date;
    private Goods goods;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyCont() {
        return replyCont;
    }

    public void setReplyCont(String replyCont) {
        this.replyCont = replyCont;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", content='" + content + '\'' +
                ", replyCont='" + replyCont + '\'' +
                ", state=" + state +
                ", date='" + date + '\'' +
                ", goods=" + goods +
                ", user=" + user +
                '}';
    }
}

