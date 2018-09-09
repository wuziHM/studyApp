package com.example.test.myapplicationtest.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.home.activities.CommentReplyActivity;

import java.util.List;

public class ReplyCommAdapter extends BaseAdapter {
    private List<TestBean> data;

    private Context context;

    public ReplyCommAdapter( Context context,List<TestBean> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size ( );
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder ( );
            view = LayoutInflater.from ( context ).inflate ( R.layout.list_item_reply_comm, null );
            vh.tv_user_name = (TextView) view.findViewById ( R.id.tv_user_name );
//            vh.tv_coment = (TextView) view .findViewById ( R.id.tv_coment );
//            vh.tv_date = (TextView) view
////                    .findViewById(R.id.tv_date);
//            vh.tv_file_type = (TextView) view
//                    .findViewById( R.id.tv_file_type  );

            view.setTag ( vh );
        } else {
            vh = (ViewHolder) view.getTag ( );
        }
        vh.tv_user_name.setText ( data.get ( i ).getName () );
        return view;
    }
    class ViewHolder {
        //        TextView tv_file_type;
//        TextView tv_date;
        TextView  tv_user_name;
        TextView tv_coment;
    }
}
