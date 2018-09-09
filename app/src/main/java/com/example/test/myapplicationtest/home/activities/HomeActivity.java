package com.example.test.myapplicationtest.home.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.find.activites.FindActivity;
import com.example.test.myapplicationtest.home.adapter.TestNormalAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RollPagerView mRollViewPager;

    /**
     * 学习管理
     */
    private TextView tv_stu;

    //资料下载
    private TextView tv_res_load;

    //优秀问答
    private TextView tv_ex_request;

    //我的问答
    private TextView tv_my_request;

    //公告栏
    private TextView tv_announce;

    //找名师
    private TextView tv_find_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );
        initView();
        initImagView();
    }

    private void initImagView() {
        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter ());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView (this, Color.YELLOW,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }

    private void initView() {
        tv_stu = (TextView) findViewById ( R.id.tv_stu );
        tv_stu.setOnClickListener (this );

        tv_res_load = (TextView) findViewById ( R.id.tv_res_load );
        tv_res_load.setOnClickListener ( this );

        tv_ex_request = (TextView) findViewById ( R.id.tv_ex_request );
        tv_ex_request.setOnClickListener ( this );

        tv_my_request = (TextView) findViewById ( R.id.tv_my_request );
        tv_my_request.setOnClickListener ( this );

        tv_announce = (TextView) findViewById ( R.id.tv_announce );
        tv_announce.setOnClickListener ( this );

        tv_find_teacher = (TextView)findViewById ( R.id.tv_find_teacher );
        tv_find_teacher.setOnClickListener ( this );


    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId ()) {
            case R.id.tv_stu:
                Intent intent = new Intent ( this, StuManageActivity.class );
                this.startActivity ( intent );
                break;

            case R.id.tv_res_load:
                Intent load_intent = new Intent ( this, StuResActivity.class );
                load_intent.putExtra ( "flag",1 ); //flag标识从那边进入资源页
                this.startActivity ( load_intent );
                break;
            case R.id.tv_ex_request:
                Intent ex_request_intent = new Intent ( this, ExRequestActivity.class );
                this.startActivity ( ex_request_intent );
                break;
            case R.id.tv_my_request:
                //传递一个flag 标识从哪边进入问答
                Intent my_request_intent =  new Intent ( this, ExRequestActivity.class );
                this.startActivity ( my_request_intent );
                break;
            case R.id.tv_announce:
                Intent announce_intent =  new Intent ( this, AnnounceActivity.class );
                this.startActivity ( announce_intent );
                break;
            case R.id.tv_find_teacher:
                Intent find_intent =  new Intent ( this, FindActivity.class );
                this.startActivity ( find_intent );
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
