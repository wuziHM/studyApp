package com.example.test.myapplicationtest.find.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.home.activities.StuManageActivity;

public class ContactDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView  tv_reback;
    private TextView tv_ward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_contact_detail );

        initView();
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );

        tv_ward = (TextView) findViewById ( R.id.tv_ward );
        tv_ward.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_reback:
               this.finish ();
                break;
            case R.id.tv_ward:
                Intent intent = new Intent ( this,WardActivity.class );
                this.startActivity(intent);
                break;
        }
    }
}
