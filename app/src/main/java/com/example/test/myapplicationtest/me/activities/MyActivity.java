package com.example.test.myapplicationtest.me.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.test.myapplicationtest.R;


public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_my );
        initData();
    }

    private void initData() {
//        String json = "{\"name\":\"李三\",\"sex\":\"男\",\"stu_num\":\"6262\"}";
//        StudentModel bean = GsonImpl.get().toObject(json,StudentModel.class);
//        String str =  GsonImpl.get ().toJson ( bean );
    }
}
