package com.example.test.myapplicationtest.dto;

import java.util.List;

/**
 * 警告列表dto
 */
public class WarningListDto {

    /**
     * currPageNo : 1
     * start : 0
     * questions : [{"member_id":3,"oper_time":"sad","id":3,"content":"4"}]
     * totalPageCount : 2
     * pageSize : 0
     * totalCount : 13
     */

    private int currPageNo;
    private int start;
    private int totalPageCount;
    private int pageSize;
    private int totalCount;
    private List<QuestionsBean> questions;

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

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * member_id : 3
         * oper_time : sad
         * id : 3
         * content : 4
         */

        private int member_id;
        private String oper_time;
        private int id;
        private String content;

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getOper_time() {
            return oper_time;
        }

        public void setOper_time(String oper_time) {
            this.oper_time = oper_time;
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
}
