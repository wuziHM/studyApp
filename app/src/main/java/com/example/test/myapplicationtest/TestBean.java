package com.example.test.myapplicationtest;

public class TestBean {
    private String name;
    private String sex;
    private String num;

    public TestBean(String name, String sex, String num) {
        this.name = name;
        this.sex = sex;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getNum() {
        return num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
