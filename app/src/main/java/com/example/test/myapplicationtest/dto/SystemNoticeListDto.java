package com.example.test.myapplicationtest.dto;

import java.util.List;

public class SystemNoticeListDto {

    /**
     * currPageNo : 1
     * start : 0
     * questions : [{"order_seq":1,"oper_time":"2","id":1,"title":"安全","content":"1"}]
     * totalPageCount : 1
     * pageSize : 0
     * totalCount : 3
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
         * order_seq : 1
         * oper_time : 2
         * id : 1
         * title : 安全
         * content : 1
         */

        private int order_seq;
        private String oper_time;
        private int id;
        private String title;
        private String content;

        public int getOrder_seq() {
            return order_seq;
        }

        public void setOrder_seq(int order_seq) {
            this.order_seq = order_seq;
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
}
