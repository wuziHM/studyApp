package com.example.test.myapplicationtest.find.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.find.adapter.ContactAdapter;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity {

    private Context mContext;

    private SwipeMenuListView lv_recent_contact;

    private  ArrayList<TestBean> data = new ArrayList<> (  );
    private ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_find );
        mContext = this;
        initListView();
    }

    private void initListView() {
        lv_recent_contact = (SwipeMenuListView) findViewById ( R.id.lv_recent_contact );
        initListViewData ();
        SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable ( Color.parseColor("#FF23AA96")));//设置背景
                deleteItem.setWidth(150);//设置滑出 项 宽度
                deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.parseColor("#ffffff"));
                deleteItem.setTitleSize(14);
//deleteItem.setIcon(R.drawable.near_delete);//没有删除俩字而是直接一个删除图标时.
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        lv_recent_contact.setMenuCreator ( menuCreator );

        //整个item的点击事件
        lv_recent_contact.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent (mContext,ContactDetailActivity.class);
                mContext.startActivity ( intent );
                Toast.makeText(mContext, "你点击了这一行",
                        Toast.LENGTH_SHORT).show();
            }
        } );

        //删除按钮的点击事件
        lv_recent_contact.setOnMenuItemClickListener ( new SwipeMenuListView.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                 // 处理逻辑
                        data.remove ( position );
                        adapter.notifyDataSetChanged();
                        //后台数据库删除行
                        break;
                }
                return false;
            }
        } );
    }

    /**
     * 加载最近联系人数据
     */
    private void initListViewData() {
        data = new ArrayList<> (  );
        TestBean bean1 = new TestBean ( "张三", "男", "13566" );
        TestBean bean2 = new TestBean ( "李思", "女", "1377666777" );
        data.add ( bean1 );
        data.add ( bean2 );
        adapter = new ContactAdapter ( this,data );
        lv_recent_contact.setAdapter ( adapter );

    }


}
