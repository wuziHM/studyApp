package com.example.test.myapplicationtest.home.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.dto.ExpericenceDto;
import com.example.test.myapplicationtest.home.adapter.StuNotesAdapter;
import com.example.test.myapplicationtest.service.StuAndTecService;

import java.util.ArrayList;
import java.util.List;

public class StuNotesActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;

    private TextView tv_addnote;

    private ListView lv_mynotes;

    private StuAndTecService mStuAndTecService;

    private int masterPupilId = 1;

    private Context mContext;

    private List<ExpericenceDto> result = new ArrayList<ExpericenceDto>();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {//根据message做逻辑处理
                /*获取t讨论组成员完成*/
                case StuAndTecService.GETEXPERIENCE:
                    result = (List<ExpericenceDto>) msg.obj;
                    StuNotesAdapter adapter = new StuNotesAdapter(mContext, result);
                    lv_mynotes.setAdapter(adapter);
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_notes);
        mStuAndTecService = new StuAndTecService(this);
        mStuAndTecService.setHandler(mHandler);
        mContext = this;
        initView();
    }

    private void initView() {
        tv_reback = (TextView) findViewById(R.id.tv_reback);

        tv_addnote = (TextView) findViewById(R.id.tv_addnotes);

        lv_mynotes = (ListView) findViewById(R.id.lv_mynotes);

        tv_reback.setOnClickListener(this);

        tv_addnote.setOnClickListener(this);

        initListView();
    }

    private void initListView() {
        new Thread() {
            @Override
            public void run() {
                mStuAndTecService.getExperience(masterPupilId);

            }
        }.start();
//        ArrayList<TestBean> data = new ArrayList<> (  );
//        TestBean bean1 = new TestBean ( "张三", "男", "13566" );
//        TestBean bean2 = new TestBean ( "李思", "女", "1377666777" );
//        data.add ( bean1 );
//        data.add ( bean2 );
//        StuNotesAdapter adapter = new StuNotesAdapter ( this,data );
//        lv_mynotes.setAdapter ( adapter );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_reback:
                this.finish();
                break;
            case R.id.tv_addnotes:
                Intent intent = new Intent(this, NotesDetailActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
