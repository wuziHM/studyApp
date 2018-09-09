package com.example.test.myapplicationtest.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.TestBean;
import com.example.test.myapplicationtest.dto.WarningListDto;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class WarningNoticeAdapter extends BaseAdapter {
    private Context context;
    private List<WarningListDto.QuestionsBean> data;

    public WarningNoticeAdapter(Context context, List<WarningListDto.QuestionsBean> data) {
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
            view = LayoutInflater.from ( context ).inflate ( R.layout.list_item_warning, null );
            vh.tv_date = (TextView) view.findViewById ( R.id.tv_date );
            vh.tv_coment = (TextView) view .findViewById ( R.id.tv_content );
            vh.tv_photo = (TextView) view .findViewById ( R.id.tv_photo );
            vh.tv_name = (TextView) view.findViewById ( R.id.tv_name ) ;
            view.setTag ( vh );
        } else {
            vh = (ViewHolder) view.getTag ( );
        }
        vh.tv_coment.setText ( data.get ( i ).getContent ()+"");
        vh.tv_name.setText ( data.get ( i ).getMember_id ()+"" );
        vh.tv_date.setText ( data.get ( i ).getOper_time ()+"" );
        //通过URL加载图片
//        ImageLoader.getInstance().displayImage(data.get ( i )., vh.tv_photo);

        vh.tv_name.setText ( data.get ( i ).getMember_id ()+"" );
        return view;
    }
    class ViewHolder {
        //        TextView tv_file_type;
//        TextView tv_date;
        TextView  tv_date;
        TextView tv_coment;
        TextView tv_photo;
        TextView tv_name;
    }
}
