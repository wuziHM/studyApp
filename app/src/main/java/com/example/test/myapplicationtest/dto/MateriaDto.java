package com.example.test.myapplicationtest.dto;

import java.util.List;

public class MateriaDto {

    /**
     * currPageNo : 1
     * start : 0
     * questions : [{"file_path":"teste_file_2.doc","create_time":"2018-08-20 12:00:00","file_name":"teste_file_2.doc","oper_name":"王","id":2,"file_size":""}]
     * totalPageCount : 1
     * pageSize : 0
     * totalCount : 2
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
         * file_path : teste_file_2.doc
         * create_time : 2018-08-20 12:00:00
         * file_name : teste_file_2.doc
         * oper_name : 王
         * id : 2
         * file_size :
         */

        private String file_path;
        private String create_time;
        private String file_name;
        private String oper_name;
        private int id;
        private String file_size;
        private String file_type;

        public String getFile_type() {
            return file_type;
        }

        public void setFile_type(String file_type) {
            this.file_type = file_type;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getOper_name() {
            return oper_name;
        }

        public void setOper_name(String oper_name) {
            this.oper_name = oper_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }
    }
}
