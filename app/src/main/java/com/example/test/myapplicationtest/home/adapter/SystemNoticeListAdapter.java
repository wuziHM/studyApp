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
import com.example.test.myapplicationtest.dto.QuestionsBean;
import com.example.test.myapplicationtest.dto.SystemNoticeListDto;
import com.example.test.myapplicationtest.home.activities.CommentReplyActivity;

import java.util.List;

public class SystemNoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<SystemNoticeListDto.QuestionsBean> data;

    public SystemNoticeListAdapter(Context context, List<SystemNoticeListDto.QuestionsBean> data) {
        this.context = context;
        this.data = data;
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
            view = LayoutInflater.from ( context ).inflate ( R.layout.list_item_notice, null );
            vh.tv_date = (TextView) view.findViewById ( R.id.tv_date );
            vh.tv_coment = (TextView) view .findViewById ( R.id.tv_content );
            view.setTag ( vh );
        } else {
            vh = (ViewHolder) view.getTag ( );
        }
        vh.tv_coment.setText ( data.get ( i ).getContent () );
        vh.tv_date.setText ( data.get ( i ).getOper_time () );
        return view;
    }
    class ViewHolder {
        //        TextView tv_file_type;
//        TextView tv_date;
        TextView  tv_date;
        TextView tv_coment;
    }
}
