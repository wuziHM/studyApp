package com.example.test.myapplicationtest.home.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.dto.QuestionsBean;
import com.example.test.myapplicationtest.dto.RefreshListDto;
import com.example.test.myapplicationtest.dto.SystemNoticeListDto;
import com.example.test.myapplicationtest.dto.WarningListDto;
import com.example.test.myapplicationtest.home.adapter.SystemNoticeAdapter;
import com.example.test.myapplicationtest.home.adapter.SystemNoticeListAdapter;
import com.example.test.myapplicationtest.home.adapter.WarningNoticeAdapter;
import com.example.test.myapplicationtest.service.SystemAnnouceService;
import com.example.test.myapplicationtest.service.dto.PdaResponse;
import com.example.test.myapplicationtest.service.dto.ResponseDto;
import com.example.test.myapplicationtest.utils.GsonImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnounceActivity extends AppCompatActivity implements View.OnClickListener {

    private static Gson gson = null;
    private TextView tv_reback;

    private ListView lv_announce;

    private TextView tv_security_notice;
    private TextView tv_system_notice;
    private TextView tv_warning_notice;

    private Context mContext;

    private SystemAnnouceService systemAnnouceService;

    //flag 标志 警示信息 0 系统消息 1 安全公告 2
    private Integer flag = 0;

    private  ArrayList<TestBean> data = new ArrayList<> (  );

    private ResponseDto result = new ResponseDto ();

    private SystemNoticeListDto noticeResult = new SystemNoticeListDto ();

    private WarningListDto warningResult = new WarningListDto ();

    private List<QuestionsBean> mQuestionsBean = new ArrayList<QuestionsBean> ( );

    private List<SystemNoticeListDto.QuestionsBean> NoticeQuestionsBean = new ArrayList<SystemNoticeListDto.QuestionsBean> ( );

    private List<WarningListDto.QuestionsBean> warningQuestionsBean = new ArrayList<WarningListDto.QuestionsBean> (  );

    private Handler mHandler = new Handler (){
        public void handleMessage(Message msg){
            switch(msg.what){//根据message做逻辑处理
                /*获取安全知识列表完成*/
                case SystemAnnouceService.GETSAFETYLIST:
                    result = (ResponseDto) msg.obj;
                    RefreshListDto mRefreshListDto =  result.getData ();
                    mQuestionsBean = mRefreshListDto.getQuestions ();
                    SystemNoticeAdapter adapter = new SystemNoticeAdapter ( mContext,mQuestionsBean );
                    lv_announce.setAdapter ( adapter );
                    break;
                case SystemAnnouceService.GETNOTICELIST:
                    noticeResult = (SystemNoticeListDto) msg.obj;
                    NoticeQuestionsBean = noticeResult.getQuestions ();
                    SystemNoticeListAdapter adapterNotice = new SystemNoticeListAdapter ( mContext,NoticeQuestionsBean );
                    lv_announce.setAdapter ( adapterNotice );
                    break;
                case SystemAnnouceService.GETWARNINGLIST:
                    warningResult = (WarningListDto) msg.obj;
                    warningQuestionsBean = warningResult.getQuestions ();
                    WarningNoticeAdapter adapterWarning = new WarningNoticeAdapter ( mContext,warningQuestionsBean );
                    lv_announce.setAdapter ( adapterWarning );
                    break;

            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_announce );
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);


        mContext = this;
        systemAnnouceService = new SystemAnnouceService ( this );
        systemAnnouceService.setHandler ( mHandler );
        initView();
        initWarninView ();
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );

        tv_security_notice = (TextView)findViewById ( R.id.tv_security_notice );
        tv_system_notice = (TextView)findViewById ( R.id.tv_system_notice );
        tv_warning_notice = (TextView)findViewById ( R.id.tv_warning_notice );

        tv_security_notice.setOnClickListener ( this );
        tv_system_notice.setOnClickListener ( this );
        tv_warning_notice.setOnClickListener ( this );

        lv_announce = (ListView) findViewById ( R.id.lv_announce );
        lv_announce.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(flag == 0){//警告
                    Intent intent = new Intent ( mContext, WarningDetailActivity.class );
                    intent.putExtra ( "memberId", warningQuestionsBean.get ( i ).getMember_id ());
                    mContext.startActivity ( intent );
                }else if(flag == 1){//系统
                    Intent intent = new Intent ( mContext, NoticeDetailActivity.class );
                    intent.putExtra ( "id", NoticeQuestionsBean.get ( i ).getId ());
                    intent.putExtra ( "flag",1 ); //flag  1标记从系统消息列表来
                    mContext.startActivity ( intent );

                }else if(flag == 2){//安全
                    Intent intent = new Intent ( mContext, NoticeDetailActivity.class );
                    intent.putExtra ( "id", warningQuestionsBean .get ( i ).getId ());
                    intent.putExtra ( "flag",2 );//flag  2标记从安全消息列表来
                    mContext.startActivity ( intent );
                }

            }
        } );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            case R.id.tv_security_notice:
                data.clear();
                lv_announce.clearFocus ();
                flag = 2;
                initSecurityView();
                break;
            case R.id.tv_system_notice:
                data.clear();
                lv_announce.clearFocus ();
                flag = 1;
                initSystemView();
                break;
            case R.id.tv_warning_notice:
                data.clear();
                lv_announce.clearFocus ();
                flag = 0;
                initWarninView();
                break;
            default:
                break;
        }
    }

    /**
     * load警告
     */
    private void initWarninView() {
        new Thread(){
            @Override
            public void run() {
                Map<String,String>  param =new HashMap<String,String>();
                param.put ( "pageNo","1" );
                systemAnnouceService.getWarningList ( param );
//                systemAnnouceService.getSafetyList ( param );
            }
        }.start();
    }

    /**
     * load系统公告
     */
    private void initSystemView() {
        new Thread(){
            @Override
            public void run() {
                Map<String,String>  param =new HashMap<String,String>();
                param.put ( "pageNo","1" );
                systemAnnouceService.getNoticeList ( param );
//                systemAnnouceService.getSafetyList ( param );
            }
        }.start();
    }


    /**
     * load安全知识
     */
    private void initSecurityView() {
//        启动一个新线程的代码：
        new Thread(){
            @Override
            public void run() {
                Map<String,String>  param =new HashMap<String,String>();
                param.put ( "pageNo","1" );
//                systemAnnouceService.getNoticeList ( param );
                systemAnnouceService.getSafetyList ( param );
            }
        }.start();

//        requestPost(param);
//----------------------------------------------------
//        String pageNo = "1";
////        systemAnnouceService.toJson(request);
//        systemAnnouceService.getSafetyList ( pageNo,true );
//       data = new ArrayList<> (  );

    }

    /**
     * 获取json实例
     * @return
     */
    public Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
