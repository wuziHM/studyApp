package com.example.test.myapplicationtest.dto;

public class QuestionsBean {

    /**
     * oper_time : 11:20
     * banner : http://pic2.pedaily.cn/201609/20169271246505723.jpg
     * id : 1
     * title : 火灾
     * content : 火灾是指在时间或空间上失去控制的灾害性燃烧现象。在各种灾害中，火灾是最经常、最普遍地威胁公众安全和社会发展的主要灾害之一。
     */

    private String oper_time;
    private String banner;
    private int id;
    private String title;
    private String content;

    public String getOper_time() {
        return oper_time;
    }

    public void setOper_time(String oper_time) {
        this.oper_time = oper_time;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}