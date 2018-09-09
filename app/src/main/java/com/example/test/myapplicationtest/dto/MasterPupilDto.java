package com.example.test.myapplicationtest.dto;

public class MasterPupilDto {

    /**
     * masterId : 1
     * finalScore : null
     * pupilId : 2
     * trainStatus : null
     * adviserId : 5
     * worktype : null
     * startTime : null
     * id : 1
     * endTime : null
     * prospectus : null
     * title : 教学组1
     * isEnd : false
     */

    private int masterId;
    private Object finalScore;
    private int pupilId;
    private Object trainStatus;
    private int adviserId;
    private Object worktype;
    private Object startTime;
    private int id;
    private Object endTime;
    private Object prospectus;
    private String title;
    private boolean isEnd;

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public Object getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Object finalScore) {
        this.finalScore = finalScore;
    }

    public int getPupilId() {
        return pupilId;
    }

    public void setPupilId(int pupilId) {
        this.pupilId = pupilId;
    }

    public Object getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(Object trainStatus) {
        this.trainStatus = trainStatus;
    }

    public int getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(int adviserId) {
        this.adviserId = adviserId;
    }

    public Object getWorktype() {
        return worktype;
    }

    public void setWorktype(Object worktype) {
        this.worktype = worktype;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public Object getProspectus() {
        return prospectus;
    }

    public void setProspectus(Object prospectus) {
        this.prospectus = prospectus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
