package com.example.test.myapplicationtest.service.dto;

import com.example.test.myapplicationtest.utils.CommonUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Type;

public class PdaResponse<T> implements Serializable {

    private static final long serialVersionUID = -7816945325851639128L;

    private boolean result;

    private String message;

    private T data;

    private long total = 0;

    public PdaResponse() {
        this(false, "", null);
    }

    public PdaResponse(boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return result;
    }

    public void setSuccess(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public PdaResponse<T> fromJson(String result) {
        Gson gson = new Gson();
        if (CommonUtils.isBlank(result)) {
            return new PdaResponse<T>();
        }
        return gson.fromJson(result, this.getClass());
    }

    public PdaResponse<T> fromJson(String result, Type typeOfT) {
        Gson gson = new Gson ();
        if (CommonUtils.isBlank(result)) {
            return new PdaResponse<T>();
        }
        return gson.fromJson(result, typeOfT);
    }
}
