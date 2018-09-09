package com.example.test.myapplicationtest.home.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.home.adapter.ReQuestionAdapter;
import com.example.test.myapplicationtest.home.adapter.ResTeachAdapter;

import java.util.ArrayList;

public class ExRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    private TextView tv_reback;
    private ListView lv_questions;

    private TextView tv_ask;

    private TextView tv_all;

    //popwidow选择
    private TextView tv_unlimit;
    private  TextView tv_reply_max;
    private TextView tv_reply_min;
    private TextView tv_time_dec;
    private TextView tv_time_asc;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ex_request );
        mContext = this;
        initView();
    }

    private void initView() {
        tv_reback = (TextView) findViewById ( R.id.tv_reback );
        tv_reback.setOnClickListener ( this );

        tv_ask = (TextView) findViewById ( R.id.tv_ask );
        tv_ask.setOnClickListener ( this );

        tv_all = (TextView) findViewById ( R.id.tv_all ) ;
        tv_all.setOnClickListener ( this );

        initListView();

    }

    private void initListView() {
        lv_questions = (ListView) findViewById ( R.id.lv_questions );

        ArrayList<TestBean> data = new ArrayList<> (  );
        TestBean bean1 = new TestBean ( "张三", "男", "13566" );
        TestBean bean2 = new TestBean ( "李思", "女", "1377666777" );
        data.add ( bean1 );
        data.add ( bean2 );
        ReQuestionAdapter adapter = new ReQuestionAdapter ( this,data );
        lv_questions.setAdapter ( adapter );

        lv_questions.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView< ?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, "这是第"+ i + "行",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent ( mContext, QuestionDetailActivity.class );
                mContext.startActivity ( intent );

            }
        } );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()) {
            case R.id.tv_reback:
                this.finish ( );
                break;
            case R.id.tv_ask:
                Intent intent = new Intent ( this,ToAskActivity.class );
                this.startActivity ( intent );
                break;
            case R.id.tv_all:
                showPopupWindow(view);
                break;
            case R.id.tv_reply_max:
                //选择问答排序回复最多
                tv_reply_max.setTextColor (getResources().getColor ( R.color.colorTitle )  );
                popupWindow.dismiss ();
                tv_all.setTextColor ( getResources().getColor ( R.color.colorTitle )  );
                Toast.makeText(mContext, "button is pressed",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_reply_min:
                //选择问答排序回复最少
                tv_reply_min.setTextColor (getResources().getColor ( R.color.colorTitle )  );
                popupWindow.dismiss ();
                tv_all.setTextColor ( getResources().getColor ( R.color.colorTitle )  );
                Toast.makeText(mContext, "button is pressed",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_time_asc:
                //选择问答排序时间升序
                tv_time_asc.setTextColor (getResources().getColor ( R.color.colorTitle )  );
                popupWindow.dismiss ();
                tv_all.setTextColor ( getResources().getColor ( R.color.colorTitle )  );
                Toast.makeText(mContext, "button is pressed",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_time_dec:
                //选择问答排序时间降序
                tv_time_dec.setTextColor (getResources().getColor ( R.color.colorTitle )  );
                popupWindow.dismiss ();
                tv_all.setTextColor ( getResources().getColor ( R.color.colorTitle )  );
                Toast.makeText(mContext, "button is pressed",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_unlimit:
                //选择问答排序不限
                tv_unlimit.setTextColor (getResources().getColor ( R.color.colorTitle )  );
                popupWindow.dismiss ();
                tv_all.setTextColor ( getResources().getColor ( R.color.colorTitle )  );
                Toast.makeText(mContext, "button is pressed",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 选择问答排序方式
     * @param view
     */
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.pop_window_select, null);
        tv_unlimit = (TextView) contentView.findViewById ( R.id.tv_unlimit );
        tv_reply_max = (TextView) contentView.findViewById ( R.id.tv_reply_max);
        tv_reply_min = (TextView) contentView.findViewById ( R.id.tv_reply_min );
        tv_time_dec = (TextView) contentView.findViewById ( R.id.tv_time_dec );
        tv_time_asc = (TextView) contentView.findViewById ( R.id.tv_time_asc );

        tv_unlimit.setOnClickListener ( this );
        tv_reply_max.setOnClickListener ( this );
        tv_reply_min.setOnClickListener ( this );
        tv_time_dec.setOnClickListener ( this );
        tv_time_asc.setOnClickListener ( this );

        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener () {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.color.white));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }
}
