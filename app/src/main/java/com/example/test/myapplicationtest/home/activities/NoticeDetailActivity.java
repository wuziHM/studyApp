package com.example.test.myapplicationtest.home.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.dto.NoticeDetailDto;
import com.example.test.myapplicationtest.dto.RefreshListDto;
import com.example.test.myapplicationtest.dto.SystemNoticeListDto;
import com.example.test.myapplicationtest.dto.WarningListDto;
import com.example.test.myapplicationtest.home.adapter.SystemNoticeAdapter;
import com.example.test.myapplicationtest.home.adapter.SystemNoticeListAdapter;
import com.example.test.myapplicationtest.home.adapter.WarningNoticeAdapter;
import com.example.test.myapplicationtest.service.SystemAnnouceService;
import com.example.test.myapplicationtest.service.dto.ResponseDto;

import java.util.HashMap;
import java.util.Map;

public class NoticeDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;
    private TextView tv_content;
    private TextView tv_date;
    private int id;
    //flag  1标记从系统消息列表来 2标记从安全消息列表来
    private int flag;
    private SystemAnnouceService systemAnnouceService;
    private NoticeDetailDto result = new NoticeDetailDto();

    private Handler mHandler = new Handler (){
        public void handleMessage(Message msg){
            switch(msg.what){//根据message做逻辑处理
                /*获取安全知识列表完成*/
                case SystemAnnouceService.GETNOTICEMESSAGE:
                    result = (NoticeDetailDto) msg.obj;
                    if(null !=  result.getContent ()){
                        tv_content.setText ( result.getContent ()+"" );
                    }
                    if(null != result.getOper_time ()){
                        tv_date.setText ( result.getOper_time ()+"" );
                    }

                    break;
            }
            super.handleMessage(msg);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_notice_detail );
        systemAnnouceService = new SystemAnnouceService ( this );
        systemAnnouceService.setHandler ( mHandler );
        initView();
        initParam();
        loadData();
    }

    /**
     * 从网络获取数据
     */
    private void loadData() {
        new Thread(){
            @Override
            public void run() {
                Map<String,Integer> param =new HashMap<String,Integer> ();
                param.put ( "id",id );
//                systemAnnouceService.getNoticeList ( param );
                if (flag == 1){
                    systemAnnouceService.getNoticeMessage ( param );
                }else if (flag == 2){
                    systemAnnouceService.getSafetyMessage( param );
                }

            }
        }.start();
    }

    private void initParam() {
         id =this.getIntent().getIntExtra ( "id",-1 );
         flag =this.getIntent().getIntExtra ( "flag",-1 );
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );

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
