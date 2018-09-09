package com.example.test.myapplicationtest.home.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.home.adapter.ResTeachAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeachPlanActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_reback;

    private ListView lv_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_teach_plan );
        initView();
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );

        lv_res = (ListView) findViewById ( R.id.lv_res );

        tv_reback.setOnClickListener ( this );

        initListView();
    }

    private void initListView() {
        ArrayList<TestBean>  data = new ArrayList<> (  );
        TestBean bean1 = new TestBean ( "张三", "男", "13566" );
        TestBean bean2 = new TestBean ( "李思", "女", "1377666777" );
        data.add ( bean1 );
        data.add ( bean2 );
//        ResTeachAdapter adapter = new ResTeachAdapter ( this,data );
//        lv_res.setAdapter ( adapter );
    }

    public void onClick(View v) {
        switch (v.getId ( )) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
