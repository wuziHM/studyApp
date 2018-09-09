package com.example.test.myapplicationtest.dto;

public class WarningDetailDto {


    /**
     * oper_time : 2018-08-31
     * head_img : https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg
     * banner : xxxx.jpg
     * real_name : test2
     * id : 3
     * content : 图片信息
     */

    private String oper_time;
    private String head_img;
    private String banner;
    private String real_name;
    private int id;
    private String content;

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
