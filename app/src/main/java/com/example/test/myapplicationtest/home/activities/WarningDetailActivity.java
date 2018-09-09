package com.example.test.myapplicationtest.home.activities;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.dto.NoticeDetailDto;
import com.example.test.myapplicationtest.dto.SystemNoticeListDto;
import com.example.test.myapplicationtest.dto.WarningDetailDto;
import com.example.test.myapplicationtest.home.adapter.SystemNoticeListAdapter;
import com.example.test.myapplicationtest.home.adapter.WarningDedailListAdapter;
import com.example.test.myapplicationtest.service.SystemAnnouceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarningDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;
    private TextView tv_content;
    private TextView tv_date;

    private ListView lv_warning_detail;
    private int memberId;
    private SystemAnnouceService systemAnnouceService;

    private Context mContext;

    private List<WarningDetailDto> response = new ArrayList<WarningDetailDto> (  );

    private Handler mHandler = new Handler (){
        public void handleMessage(Message msg){
            switch(msg.what){//根据message做逻辑处理
                /*获取安全知识列表完成*/
                case SystemAnnouceService.GETWARNINGMESSAGE:
                    response = ( List<WarningDetailDto>) msg.obj;
                    WarningDedailListAdapter adapterNotice = new WarningDedailListAdapter ( mContext,response );
                    lv_warning_detail.setAdapter ( adapterNotice );
                    break;
            }
            super.handleMessage(msg);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_warning_detail );
        systemAnnouceService = new SystemAnnouceService ( this );
        systemAnnouceService.setHandler ( mHandler );
        mContext = this;
        initView();
        initParam();
        loadData();
    }

    private void loadData() {

        new Thread(){
            @Override
            public void run() {
                Map<String,Integer> param =new HashMap<String,Integer> ();
                param.put ( "memberId",memberId );
                systemAnnouceService.getWarningMessage ( param );
            }
        }.start();
    }

    private void initParam() {
        memberId =this.getIntent().getIntExtra ( "memberId",-1 );
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );
        lv_warning_detail = (ListView)findViewById ( R.id.lv_warning_detail );

        tv_date = (TextView) findViewById ( R.id.tv_date );
        tv_content = (TextView)findViewById ( R.id.tv_content );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            default:
                break;
        }
    }
}
