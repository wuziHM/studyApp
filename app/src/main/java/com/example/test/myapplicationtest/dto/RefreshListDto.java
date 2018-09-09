package com.example.test.myapplicationtest.dto;

import java.util.List;

public class RefreshListDto {

    /**
     * totalPageCount : 2
     * pageSize : 0
     * totalCount : 11
     * currPageNo : 1
     * start : 0
     * questions : [{"oper_time":"11:20","banner":"http://pic2.pedaily.cn/201609/20169271246505723.jpg","id":1,"title":"火灾","content":"火灾是指在时间或空间上失去控制的灾害性燃烧现象。在各种灾害中，火灾是最经常、最普遍地威胁公众安全和社会发展的主要灾害之一。"},{"oper_time":"12:30","banner":"http://pic2.pedaily.cn/201609/20169271246505723.jpg","id":2,"title":"高温","content":"由于近年来高温热浪天气的频繁出现，高温带来的灾害日益严重。为此，我国气象部门针对高温天气的防御，特别制定了高温预警信号"},{"oper_time":"10:10","banner":"http://pic2.pedaily.cn/201609/20169271246505723.jpg","id":3,"title":"高温","content":"由于近年来高温热浪天气的频繁出现，高温带来的灾害日益严重。为此，我国气象部门针对高温天气的防御，特别制定了高温预警信号"},{"oper_time":"09:30","banner":"http://pic2.pedaily.cn/201609/20169271246505723.jpg","id":4,"title":"火灾","content":"火灾是指在时间或空间上失去控制的灾害性燃烧现象。在各种灾害中，火灾是最经常、最普遍地威胁公众安全和社会发展的主要灾害之一。"},{"oper_time":"09:31","banner":"https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg","id":5,"title":"火灾","content":"火灾是指在时间或空间上失去控制的灾害性燃烧现象。在各种灾害中，火灾是最经常、最普遍地威胁公众安全和社会发展的主要灾害之一。"},{"oper_time":"09:33","banner":"https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg","id":6,"title":"火灾","content":"火灾是指在时间或空间上失去控制的灾害性燃烧现象。在各种灾害中，火灾是最经常、最普遍地威胁公众安全和社会发展的主要灾害之一。"},{"oper_time":"09:34","banner":"https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg","id":7,"title":"火灾","content":"火灾是指在时间或空间上失去控制的灾害性燃烧现象。在各种灾害中，火灾是最经常、最普遍地威胁公众安全和社会发展的主要灾害之一。"},{"oper_time":"09:35","banner":"https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg","id":8,"title":"高温","content":"由于近年来高温热浪天气的频繁出现，高温带来的灾害日益严重。为此，我国气象部门针对高温天气的防御，特别制定了高温预警信号"},{"oper_time":"09:36","banner":"http://pic2.pedaily.cn/201609/20169271246505723.jpg","id":9,"title":"高温","content":"由于近年来高温热浪天气的频繁出现，高温带来的灾害日益严重。为此，我国气象部门针对高温天气的防御，特别制定了高温预警信号"}]
     */

    private int totalPageCount;
    private int pageSize;
    private int totalCount;
    private int currPageNo;
    private int start;
    private List<QuestionsBean> questions;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        this.currPageNo = currPageNo;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }
}
