package com.example.test.myapplicationtest.dto;

public class ExpericenceDto {

    /**
     * masterPupilId : null
     * img : null
     * id : 1
     * content : 生活就像一个人的旅行
     * operTime : 2011-02-08
     */

    private Object masterPupilId;
    private Object img;
    private int id;
    private String content;
    private String operTime;

    public Object getMasterPupilId() {
        return masterPupilId;
    }

    public void setMasterPupilId(Object masterPupilId) {
        this.masterPupilId = masterPupilId;
    }

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
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

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }
}
