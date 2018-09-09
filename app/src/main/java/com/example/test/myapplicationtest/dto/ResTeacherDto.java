package com.example.test.myapplicationtest.dto;

import java.util.List;

public class ResTeacherDto {

    /**
     * currPageNo : 1
     * start : 0
     * questions : [{"fileName":"计算机基础与应用","fileSize":"31.0k","filePath":"www.baidu.com","fileType":"doc","operTime":"2012-11-23"}]
     * totalPageCount : 1
     * pageSize : 0
     * totalCount : 1
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
         * fileName : 计算机基础与应用
         * fileSize : 31.0k
         * filePath : www.baidu.com
         * fileType : doc
         * operTime : 2012-11-23
         */

        private String fileName;
        private String fileSize;
        private String filePath;
        private String fileType;
        private String operTime;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getOperTime() {
            return operTime;
        }

        public void setOperTime(String operTime) {
            this.operTime = operTime;
        }
    }
}
