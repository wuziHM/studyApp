package com.example.test.myapplicationtest;

import android.app.LocalActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.test.myapplicationtest.base.BaseActivity;
import com.example.test.myapplicationtest.find.activites.FindActivity;
import com.example.test.myapplicationtest.home.activities.HomeActivity;
import com.example.test.myapplicationtest.me.activities.MyActivity;

public class MainMenuActivity extends BaseActivity {

    public static final String INTENT_CURRENT_TAB = "intent_current_tab";

    /**
     * 上传缓存的坐标后直接退出应用
     */
    private static final int REQUEST_EXIT = 0x501;

    private TabHost mTabHost;

    private LocalActivityManager mActivityManager;

    private Button mRbMenuOne;
    private Button mRbMenuTwo;
    private Button mRbMenuThree;
    private Button mRbMenuFour;
    private Button mRbMenuFive;

    private TextView mTvMsgNumber;
    private TextView mTvAnnounceNumber;

    private NotificationManager mNotificationManager = null;

    private SharedPreferences mSharedPreferences;

//	private RadioGroup mRgMenuGroup;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_menu);
        mActivityManager = new LocalActivityManager(this, true);
        mActivityManager.dispatchCreate(bundle);

        initData();
        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setNumberView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void initData() {
        mNotificationManager = (NotificationManager)getSystemService( Context.NOTIFICATION_SERVICE);
    }

    private void initView() {
        mTabHost = (TabHost) findViewById(R.id.tabhost);
        mTabHost.setup(mActivityManager);

        mRbMenuOne = (Button) findViewById(R.id.rb_base_opt_one);
        mRbMenuTwo = (Button) findViewById(R.id.rb_base_opt_two);
        mRbMenuThree = (Button) findViewById(R.id.rb_base_opt_three);
        mRbMenuFour = (Button) findViewById(R.id.rb_base_opt_four);
        mRbMenuFive = (Button) findViewById(R.id.rb_base_opt_five);

        mTvMsgNumber = (TextView) findViewById(R.id.tv_msg_number);
        mTvAnnounceNumber = (TextView) findViewById(R.id.tv_announce_number);

        mRbMenuOne.setText(R.string.label_main_home);
        mRbMenuTwo.setText(R.string.person_info);
        mRbMenuThree.setText(R.string.system_info);
        mRbMenuFour.setText(R.string.label_main_message);
        mRbMenuFive.setText(R.string.label_system_announce);

//		mRgMenuGroup = (RadioGroup) findViewById(R.id.main_tab);

        try {
            mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.label_main_home))
                    .setIndicator(getString(R.string.label_main_home))
                    .setContent(getMainIntent()));
            mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.person_info))
                    .setIndicator(getString(R.string.person_info))
                    .setContent(getPersonInfoIntent()));
            mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.system_info))
                    .setIndicator(getString(R.string.system_info))
                    .setContent(getViewInfoIntent()));
            mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.label_main_message))
                    .setIndicator(getString(R.string.label_main_message))
                    .setContent(getMessageIntent()));
            mTabHost.addTab(mTabHost.newTabSpec(getString(R.string.label_system_announce))
                    .setIndicator(getString(R.string.label_system_announce))
                    .setContent(getAnnounceIntent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置主页显示
     * @author wangchangsen
     * @date 2014-2-11 下午4:40:10
     */
    public void setMainView() {
        mTabHost.setCurrentTab(0);
        mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_click), null, null);
        mRbMenuTwo.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_person_normal), null, null);
        mRbMenuThree.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_sysinfo_normal), null, null);
        mRbMenuFour.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_message_new_normal), null, null);
    }

    /**
     * 主页
     * @author wangchangsen
     * @date 2014-1-27 上午10:53:56
     * @return
     */
    public Intent getMainIntent() {
        Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);
        return intent;
    }


    private Intent getViewInfoIntent() {
        Intent intent = new Intent(this, FindActivity.class);
        return intent;
    }

    /**
     * 个人信息
     * @author wangchangsen
     * @date 2013-9-12 下午6:02:54
     * @return
     */
    private Intent getPersonInfoIntent() {
        Intent intent = new Intent(this, MyActivity.class);
        return intent;
    }

    private Intent getSystemInfoIntent() {
        Intent intent = new Intent(this, FindActivity.class);
        return intent;
    }

    /**
     * @author wangchangsen
     * @date 2014-2-7 下午3:40:39
     * @return
     */
    private Intent getMessageIntent() {
        Intent intent = new Intent(this, MyActivity.class);
        return intent;
    }

    /**
     * 系统公告页面
     * @author wangchangsen
     * @date 2014-8-25 上午11:18:55
     * @return
     */
    private Intent getAnnounceIntent() {
        Intent intent = new Intent(this, FindActivity.class);
        return intent;
    }

    private void initListener() {
        mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_click), null, null);
        mRbMenuOne.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mActivityManager.dispatchResume();
                mTabHost.setCurrentTabByTag(getString(R.string.label_main_home));
                mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_click), null, null);
                mRbMenuTwo.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_person_normal), null, null);
                mRbMenuThree.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_sysinfo_normal), null, null);
                mRbMenuFour.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_message_new_normal), null, null);
                mRbMenuFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_main_menu_system_announce_normal, 0, 0);
            }

        });

        mRbMenuTwo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mActivityManager.dispatchResume();
                mTabHost.setCurrentTabByTag(getString(R.string.person_info));
                mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_normal), null, null);
                mRbMenuTwo.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_person_click), null, null);
                mRbMenuThree.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_sysinfo_normal), null, null);
                mRbMenuFour.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_message_new_normal), null, null);
                mRbMenuFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_main_menu_system_announce_normal, 0, 0);
            }
        });
        mRbMenuThree.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mActivityManager.dispatchResume();
                mTabHost.setCurrentTabByTag(getString(R.string.system_info));
                mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_normal), null, null);
                mRbMenuTwo.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_person_normal), null, null);
                mRbMenuThree.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_sysinfo_click), null, null);
                mRbMenuFour.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_message_new_normal), null, null);
                mRbMenuFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_main_menu_system_announce_normal, 0, 0);
            }
        });
        mRbMenuFour.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mActivityManager.dispatchResume();
                mTabHost.setCurrentTabByTag(getString(R.string.label_main_message));
                mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_normal), null, null);
                mRbMenuTwo.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_person_normal), null, null);
                mRbMenuThree.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_sysinfo_normal), null, null);
                mRbMenuFour.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_message_new_click), null, null);
                mRbMenuFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_main_menu_system_announce_normal, 0, 0);
//                cancelNotifier();
            }
        });
        mRbMenuFive.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mActivityManager.dispatchResume();
                mTabHost.setCurrentTabByTag(getString(R.string.label_system_announce));
                mRbMenuOne.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_home_new_normal), null, null);
                mRbMenuTwo.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_person_normal), null, null);
                mRbMenuThree.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_sysinfo_normal), null, null);
                mRbMenuFour.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.ic_main_menu_message_new_normal), null, null);
                mRbMenuFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_main_menu_system_announce_click, 0, 0);
            }
        });

    }


    private void checkedChanged(RadioGroup group, int checkedId) {
        mActivityManager.dispatchResume();
        switch (checkedId) {
            case R.id.rb_base_opt_one: {
                mTabHost.setCurrentTabByTag(getString(R.string.label_main_home));
                break;
            }
            case R.id.rb_base_opt_two: {
                mTabHost.setCurrentTabByTag(getString(R.string.person_info));
                break;
            }
            case R.id.rb_base_opt_three: {
                mTabHost.setCurrentTabByTag(getString(R.string.system_info));
                break;
            }
            case R.id.rb_base_opt_four: {
                mTabHost.setCurrentTabByTag(getString(R.string.label_main_message));
                break;
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
//            exitPromp();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {
        super.onActivityResult(requestCode, resultCode, arg2);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_EXIT: {
//                    exit();
                    break;
                }
                default : {
                    break;
                }
            }
        }
    }

}
