package com.example.test.myapplicationtest.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.dto.ResTeacherDto;

import java.util.List;

public class ResTeachAdapter extends BaseAdapter{

    private List<ResTeacherDto.QuestionsBean> data;

    private Context context;

    public ResTeachAdapter(Context context, List<ResTeacherDto.QuestionsBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(
                    R.layout.list_item_res, null);
            vh.tv_file_name = (TextView) view
                    .findViewById(R.id.tv_file_name);
            vh.tv_date = (TextView) view
                    .findViewById(R.id.tv_date);
            vh.tv_file_type = (TextView) view
                    .findViewById( R.id.tv_file_type  );
            vh.tv_file_size = (TextView) view
                    .findViewById( R.id.tv_file_size  );
            view.setTag(vh);

        } else {
            vh = (ViewHolder) view.getTag();
        }

        vh.tv_file_name.setText ( ""+data.get ( i ).getFileName ());
        vh.tv_file_type.setText ( ""+data.get ( i ).getFileType () );
        vh.tv_date.setText (""+ data.get ( i ).getOperTime ( ));
        vh.tv_file_size.setText ( ""+ data.get ( i ).getFileSize ());
        return view;
    }

    class ViewHolder {
        TextView tv_file_type;
        TextView tv_date;
        TextView  tv_file_name;
        TextView tv_file_size;
    }
}
