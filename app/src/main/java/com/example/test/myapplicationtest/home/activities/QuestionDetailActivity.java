package com.example.test.myapplicationtest.home.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.home.adapter.ReQuestionAdapter;
import com.example.test.myapplicationtest.home.adapter.ReplyAdapter;

import java.util.ArrayList;

public class QuestionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;

    private ListView lv_reply;

    //我要回答
    private TextView tv_to_reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_question_detail );
        initView();
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );

        tv_to_reply = (TextView) findViewById ( R.id.tv_to_reply );
        tv_to_reply.setOnClickListener ( this );

        initListView();

    }

    private void initListView() {
        lv_reply = (ListView) findViewById ( R.id.lv_reply );
        ArrayList<TestBean> data = new ArrayList<> (  );
        TestBean bean1 = new TestBean ( "张三", "男", "13566" );
        TestBean bean2 = new TestBean ( "李思", "女", "1377666777" );
        data.add ( bean1 );
        data.add ( bean2 );
        ReplyAdapter adapter = new ReplyAdapter ( this,data );
        lv_reply.setAdapter ( adapter );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            case R.id.tv_to_reply:
                Intent intent = new Intent ( this,ToReplyActivity.class );
                this.startActivity ( intent );
                break;
            default:
                break;
        }
    }
}
