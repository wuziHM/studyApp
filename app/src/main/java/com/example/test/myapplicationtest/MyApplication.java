package com.example.test.myapplicationtest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        //加载网络url图片
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator ())
                .tasksProcessingOrder( QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
        //end
        //加载url圆形图片
        Fresco.initialize(this);

    }

}
