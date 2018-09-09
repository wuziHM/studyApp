package com.example.test.myapplicationtest.home.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.dto.MateriaDto;
import com.example.test.myapplicationtest.dto.NoticeDetailDto;
import com.example.test.myapplicationtest.dto.ResTeacherDto;
import com.example.test.myapplicationtest.home.adapter.ResMateriaAdapter;
import com.example.test.myapplicationtest.home.adapter.ResTeachAdapter;
import com.example.test.myapplicationtest.service.ResourceService;
import com.example.test.myapplicationtest.service.SystemAnnouceService;
import com.example.test.myapplicationtest.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StuResActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;

    private TextView tv_upload;

    private EditText edt_res_search;

    private final int charMaxNum = 10; // 允许输入的字数

    private CharSequence temp;    // 监听前的文本

    private int editStart;      // 光标开始位置

    private int editEnd;        // 光标结束位置

    private Context mContext;

    private ListView lv_res;

    private int pageNo = 1;

    private static int FILE_SELECT_CODE = 1;

    private int flag = 0;

    private String searchWord;

    private ResourceService resourceService;

    private ResTeacherDto result = new ResTeacherDto();

    private MateriaDto result1 = new  MateriaDto();

    private List<MateriaDto.QuestionsBean> resQuestionsBeans2 = new ArrayList<MateriaDto.QuestionsBean> (  );

    private List<ResTeacherDto.QuestionsBean> resQuestionsBeans = new ArrayList<ResTeacherDto.QuestionsBean > ();

    private Handler mHandler = new Handler (){
        public void handleMessage(Message msg){
            switch(msg.what){//根据message做逻辑处理
                /*获取安全知识列表完成*/
                case ResourceService.GETMASPUPMASTERIALLIST:
                    result = (ResTeacherDto) msg.obj;
                    resQuestionsBeans = result.getQuestions ();
                    ResTeachAdapter adapter = new ResTeachAdapter (mContext,resQuestionsBeans );
                    lv_res.setAdapter ( adapter );
                    break;
                case ResourceService.GETMATERIALLIST:
                    result1 = (MateriaDto) msg.obj;
                    resQuestionsBeans2 = result1.getQuestions ();
                    ResMateriaAdapter adapter1 = new ResMateriaAdapter (mContext,resQuestionsBeans2 );
                    lv_res.setAdapter ( adapter1 );
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_stu_res );
        mContext = this;
        resourceService = new ResourceService ( this );
        resourceService.setHandler ( mHandler );
        initParam();
        initView();
    }

    private void initParam() {
        flag = this.getIntent ().getIntExtra ( "flag",0 );
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_upload = (TextView) findViewById ( R.id.tv_upload );

        tv_upload.setOnClickListener ( this );

        edt_res_search = (EditText) findViewById ( R.id.edt_res_search );
        lv_res = (ListView) findViewById ( R.id.lv_res );

        tv_reback.setOnClickListener ( this );

        initListView();

        edt_res_search.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        // 输入文本之前的状态
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        // 输入文本中的状态
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s; //temp = s   用于记录当前正在输入文本的个数
        }
        // 输入文本之后的状态
        @Override
        public void afterTextChanged(Editable s) {
            editStart = edt_res_search.getSelectionStart();
            editEnd = edt_res_search.getSelectionEnd();
//            main_tv.setText("" + temp.length());  //把输入temp中记录的字符个数显示在TextView上
            if (temp.length() >2) {
                new Thread(){
                    @Override
                    public void run() {
                        if(flag == 0){
                            resourceService.getMasPupMasterialList ( pageNo,edt_res_search.getText ().toString ().trim (),1);
                        }else if(flag == 1){
                            resourceService.getMaterialList (pageNo,edt_res_search.getText ().toString ().trim ()  );
                        }
//
                    }
                }.start();
            }
        }
    };
    private void initListView() {
        new Thread(){
            @Override
            public void run() {
                if(flag == 0){
                    resourceService.getMasPupMasterialList ( pageNo,"",1);
                }else if(flag == 1){
                    resourceService.getMaterialList (pageNo,""  );
                }

            }
        }.start();
    }

    public void onClick(View v) {
        switch (v.getId ( )) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            case R.id.tv_upload:
                showFileChooser();
            default:
                break;
        }
    }
//文件选择
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //选择文件后
            case 1:
                if (resultCode == RESULT_OK) {
// Get the Uri of the selected file
                    Uri uri = data.getData();
                    String path = FileUtils.getPath(this, uri);
                    if(flag == 0){
                        //讨论组入口
                        resourceService.uploadMaterial (1,1,path);
                    }else if(flag == 1){//home系统入口
                        resourceService.uploadMaterial (1,0,path);

                    }

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
