package com.example.test.myapplicationtest.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<TestBean> data;

    private Context context;

    public ContactAdapter(Context context,List<TestBean> data) {
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
            view = LayoutInflater.from ( context ).inflate ( R.layout.list_item_contacts, null );
            vh.tv_que_title = (TextView) view.findViewById ( R.id.tv_name );
//            vh.tv_date = (TextView) view
//                    .findViewById(R.id.tv_date);
//            vh.tv_file_type = (TextView) view
//                    .findViewById( R.id.tv_file_type  );
            view.setTag ( vh );
        } else {
            vh = (ViewHolder) view.getTag ( );
        }
        vh.tv_que_title.setText ( data.get ( i ).getName () );
        return view;
    }

    class ViewHolder {
        //        TextView tv_file_type;
//        TextView tv_date;
        TextView  tv_que_title;
    }
}
