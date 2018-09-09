package com.example.test.myapplicationtest.dto;

public class EvaluateDto {

    /**
     * masterPupilId : null
     * evaTime : 2012-01-04
     * id : 1
     * status : 1
     */

    private Object masterPupilId;
    private String evaTime;
    private int id;
    private int status;

    public Object getMasterPupilId() {
        return masterPupilId;
    }

    public void setMasterPupilId(Object masterPupilId) {
        this.masterPupilId = masterPupilId;
    }

    public String getEvaTime() {
        return evaTime;
    }

    public void setEvaTime(String evaTime) {
        this.evaTime = evaTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
