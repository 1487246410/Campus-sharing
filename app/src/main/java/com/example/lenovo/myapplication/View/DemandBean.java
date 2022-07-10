package com.example.lenovo.myapplication.View;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * 需求
 */
public class DemandBean extends BmobObject {


    private BmobUser user;
    // 内容
    private String content;
    // 价格
    private String price;
    // 电话
    private String phone;
    // 时间
    private String time;
    // 地址
    private String address;
    //    标题
    private String biaoti;

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }
}
