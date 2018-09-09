package com.example.test.myapplicationtest.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.dto.SystemNoticeListDto;
import com.example.test.myapplicationtest.dto.WarningDetailDto;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class WarningDedailListAdapter extends BaseAdapter{
    private Context context;
    private List<WarningDetailDto> data;

    public WarningDedailListAdapter(Context context, List<WarningDetailDto> data) {
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
            view = LayoutInflater.from ( context ).inflate ( R.layout.list_item_warning_detail, null );
            vh.tv_date = (TextView) view.findViewById ( R.id.tv_date );
            vh.tv_coment = (TextView) view .findViewById ( R.id.tv_content );
            vh.tv_name = (TextView) view .findViewById ( R.id.tv_name );
            vh.tv_photo = (SimpleDraweeView) view .findViewById ( R.id.tv_photo );
            view.setTag ( vh );
        } else {
            vh = (ViewHolder) view.getTag ( );
        }
        vh.tv_coment.setText ( data.get ( i ).getContent () );
        vh.tv_date.setText ( data.get ( i ).getOper_time () );
        vh.tv_name.setText ( data.get ( i ).getReal_name ());
        //加载圆形图片
        Uri uri = Uri.parse(data.get ( i ).getHead_img ());
        vh.tv_photo.setImageURI(uri);
//        ImageLoader.getInstance().displayImage(data.get ( i ).getHead_img (),  vh.tv_photo); //j加载网络图片
        return view;
    }
    class ViewHolder {
        //        TextView tv_file_type;
//        TextView tv_date;
        SimpleDraweeView tv_photo;
        TextView  tv_date;
        TextView tv_coment;
        TextView tv_name;
    }
}
