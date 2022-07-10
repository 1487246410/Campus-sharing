package com.example.lenovo.myapplication.View;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 发布
 */

public class PublishBean extends BmobObject {
    //用户
    private BmobUser user;

    //类型
    private  int type;

    //内容
    private String content;
    //价格
    private String price;
    //现金
    private String cash;
    //电话
    private String phone;
    //时间
    private String time;
    //地址
    private String address;
    // 承诺
    private String pledge;
    // 图片
    private BmobFile imageview;
    public BmobFile getImageview() {
        return imageview;
    }

    public void setImageview(BmobFile imageview) {
        this.imageview = imageview;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getPledge() {
        return pledge;
    }

    public void setPledge(String pledge) {
        this.pledge = pledge;
    }
    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
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

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
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
}
