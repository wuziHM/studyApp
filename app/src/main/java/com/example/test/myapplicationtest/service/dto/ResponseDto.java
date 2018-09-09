package com.example.test.myapplicationtest.service.dto;

import com.example.test.myapplicationtest.dto.RefreshListDto;

import java.util.List;

public class ResponseDto {
    private Number result;
    private RefreshListDto data;

    public Number getResult() {
        return result;
    }

    public void setResult(Number result) {
        this.result = result;
    }

    public RefreshListDto getData() {
        return data;
    }

    public void setData(RefreshListDto data) {
        this.data = data;
    }
}
