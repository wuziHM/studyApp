package com.example.test.myapplicationtest.home.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.home.adapter.ReplyAdapter;
import com.example.test.myapplicationtest.home.adapter.ReplyCommAdapter;

import java.util.ArrayList;

public class CommentReplyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_reback;

    private ListView lv_reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_comment_reply );
        initView();
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );

        initListView();
    }

    private void initListView() {
        lv_reply = (ListView) findViewById ( R.id.lv_reply );
        ArrayList<TestBean> data = new ArrayList<> (  );
        TestBean bean1 = new TestBean ( "张三", "男", "13566" );
        TestBean bean2 = new TestBean ( "李思", "女", "1377666777" );
        data.add ( bean1 );
        data.add ( bean2 );
        ReplyCommAdapter adapter = new ReplyCommAdapter ( this,data );
        lv_reply.setAdapter ( adapter );
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
