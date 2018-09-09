package com.example.test.myapplicationtest.home.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test.myapplicationtest.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

public class TestNormalAdapter extends StaticPagerAdapter {

    private int[] imgs = {  //下面是自己的图片，
    R.drawable.banner,
    R.drawable.banner,
    R.drawable.banner,
    R.drawable.banner,
};


@Override
    public View getView(ViewGroup container, int position) {
    ImageView view = new ImageView(container.getContext());
    view.setImageResource(imgs[position]);
    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    return view;

}

    @Override
    public int getCount() {
        return imgs.length;

    }
}
