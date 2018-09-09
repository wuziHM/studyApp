package com.example.test.myapplicationtest.home.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;

public class NotesDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;

    private TextView tv_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_notes_detail );
        initView();
    }
    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );

        tv_save = (TextView) findViewById ( R.id.tv_addnotes );

        tv_reback.setOnClickListener ( this );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            case R.id.tv_save:
                //网络存储学习心得
                break;
            default:
                break;
        }
    }
}
