package com.example.test.myapplicationtest.service;

import android.content.Context;
import android.util.Log;

import com.example.test.myapplicationtest.dto.EvaluateDto;
import com.example.test.myapplicationtest.dto.ExpericenceDto;
import com.example.test.myapplicationtest.dto.GroupUserDto;
import com.example.test.myapplicationtest.dto.MasterPupilDto;
import com.example.test.myapplicationtest.utils.GsonImpl;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class StuAndTecService extends BaseService{
    public static final String url = "http://sundatou.iok.la:18134/train/user/";//http://sundatou.iok.la:18134/train/front/
    public static final String nameSpace = "http://sundatou.iok.la:18134/train/user/";
//----------------------------------方法名-----------------------
    // 获取师徒教学资料
    public static final String MethodgetMasterPupil = "getMasterPupil";

    //获取心得体会
    public static final String MethodgetExperience = "getExperience";

    //获取讨论组评价
    public static final String MethodgetEvaluate = "getEvaluate";

    //添加心得体会
    public static final String MethodaddExperience = "addExperience";

    //添加心得图片
    public static final String MethodaddExperienceImg = "addExperienceImg";

    //学习计划打分及状态的修改
    public static final String MethodchangeStudyStatus = "changeStudyStatus";

    //修改评论状态
    public static final String MethodchangeEvaluate = "changeEvaluate";

    //获取讨论组成员
    public static final String MethodgetGroupUser = "getGroupUser";

    //----------------------------------------handler-----------------
    public static final int GETMASTERPUPIL = 0;

    public static final int GETEXPERIENCE = 1;

    public static final int GETEVALUATE = 2;

    public static final int ADDEXPERIENCE = 3;

    public static final int ADDEXPERIENCEIMG = 4;

    public static final int CHANGESTUDYSTATUS = 5;

    public static final int CHANGEEVALUATE = 6;

    public static final int GETGROUPUSER = 6;

    public void getGroupUser(int id){
        try {
            String baseUrl = url + MethodgetGroupUser+"?Id="+id;
            // 新建一个URL对象+"&materi
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "masterPupil" );
                int success = jsonObject.optInt ( "result" );
                GroupUserDto response = new GroupUserDto (  );
                response =  GsonImpl.get().toObject (str.toString (),GroupUserDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETGROUPUSER, response);
                }
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }

    /**
     * 修改评论状态
     * @param masterPupilId
     * @param status
     */
    public void changeEvaluate(int masterPupilId,String status ) {
        //使用开源Utils做上传操作
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient ( );
//url : 请求服务器的url
        asyncHttpClient.post ( url + MethodchangeEvaluate + "?masterPupilId=" + masterPupilId+"&status="+status, new AsyncHttpResponseHandler ( ) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    sendHandleMessage ( CHANGEEVALUATE, "已成功提交" );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        } );
    }


    /**
     * 学习计划打分及状态的修改
     * 情况一：如果id不为null，分数为null，则为每周学习状态修改
     情况二：如果id不为null，分数不为null，则为学习计划完成状态的修改
     * @param id
     * @param trainStatus
     * @param finalScore
     */
    public void changeStudyStatus(int id,int trainStatus,double finalScore ) {
        //使用开源Utils做上传操作
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient ( );
//url : 请求服务器的url
        asyncHttpClient.post ( url + MethodchangeStudyStatus + "?id=" + id+"&trainStatus="+trainStatus+"&finalScore="+finalScore, new AsyncHttpResponseHandler ( ) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    sendHandleMessage ( CHANGESTUDYSTATUS, "已成功提交" );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        } );
    }


    //添加心得体会图片
    public void addExperienceImg(int masterPupilId,String filepath ) {
        //使用开源Utils做上传操作
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient ( );

        RequestParams params = new RequestParams ( );
        try {
            params.put ( "image", new File ( filepath ) );
        } catch (FileNotFoundException e1) {
            e1.printStackTrace ( );
        }
//url : 请求服务器的url
        asyncHttpClient.post ( url + MethodaddExperienceImg + "?masterPupilId=" + masterPupilId, params, new AsyncHttpResponseHandler ( ) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    sendHandleMessage ( ADDEXPERIENCEIMG, "已成功提交" );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        } );
    }

        //添加心得体会
    public void addExperience(int masterPupilId,String content){
        try {
            String baseUrl = url + MethodaddExperience +"?masterPupilId="+masterPupilId+"&content="+content;
            // 新建一个URL对象+"&materi
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                int success = jsonObject.optInt ( "result" );
                if(success == 1){
                    //成功
                    sendHandleMessage(ADDEXPERIENCE, "提交成功");
                }
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }

    //获取讨论组评价
    public void getEvaluate(int masterPupilId){
        try {
                    String baseUrl = url + MethodgetEvaluate +"?masterPupilId="+masterPupilId;
                    // 新建一个URL对象+"&materi
                    URL url = new URL(baseUrl);
                    // 打开一个HttpURLConnection连接
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    // 设置连接超时时间
                    urlConn.setConnectTimeout(15 * 1000);
                    //设置从主机读取数据超时
                    urlConn.setReadTimeout(15 * 1000);
                    // Post请求必须设置允许输出 默认false
                    urlConn.setDoOutput(true);
                    //设置请求允许输入 默认是true
                    urlConn.setDoInput(true);
                    // Post请求不能使用缓存
                    urlConn.setUseCaches(false);
                    // 设置为Post请求
                    urlConn.setRequestMethod("POST");
                    //设置本次连接是否自动处理重定向
                    urlConn.setInstanceFollowRedirects(true);
                    // 配置请求Content-Type
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    // 开始连接
                    urlConn.connect();
                    // 发送请求参数
                    DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write(postData);
                    dos.flush();
                    dos.close();
                    // 判断请求是否成功
                    if (urlConn.getResponseCode() == 200) {
                        // 获取返回的数据
                        String result = streamToString(urlConn.getInputStream());
                        JSONObject jsonObject = new JSONObject(result);
                        Object str = jsonObject.opt ( "data" );
                        int success = jsonObject.optInt ( "result" );
                        List<EvaluateDto> response = new ArrayList<EvaluateDto> (  );
                        response =  GsonImpl.get().toList (str.toString (),EvaluateDto.class);
                        if(success == 1){
                            //成功
                            sendHandleMessage(GETEVALUATE, response);
                        }
                        Log.e("tag", "Post方式请求成功，result--->" + result);
                    } else {
                        Log.e("tag", "Post方式请求失败");
                    }
                    // 关闭连接
                    urlConn.disconnect();
                } catch (Exception e) {
                    Log.e("tag", e.toString());
                }
    }

//获取心得体会
    public void getExperience(int masterPupilId){
        {
            try {
                String baseUrl = url + MethodgetExperience +"?masterPupilId="+masterPupilId;
                // 新建一个URL对象+"&materi
                URL url = new URL(baseUrl);
                // 打开一个HttpURLConnection连接
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                // 设置连接超时时间
                urlConn.setConnectTimeout(15 * 1000);
                //设置从主机读取数据超时
                urlConn.setReadTimeout(15 * 1000);
                // Post请求必须设置允许输出 默认false
                urlConn.setDoOutput(true);
                //设置请求允许输入 默认是true
                urlConn.setDoInput(true);
                // Post请求不能使用缓存
                urlConn.setUseCaches(false);
                // 设置为Post请求
                urlConn.setRequestMethod("POST");
                //设置本次连接是否自动处理重定向
                urlConn.setInstanceFollowRedirects(true);
                // 配置请求Content-Type
                urlConn.setRequestProperty("Content-Type", "application/json");
                // 开始连接
                urlConn.connect();
                // 发送请求参数
                DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write(postData);
                dos.flush();
                dos.close();
                // 判断请求是否成功
                if (urlConn.getResponseCode() == 200) {
                    // 获取返回的数据
                    String result = streamToString(urlConn.getInputStream());
                    JSONObject jsonObject = new JSONObject(result);
                    Object str = jsonObject.opt ( "data" );
                    int success = jsonObject.optInt ( "result" );
                    List<ExpericenceDto> response = new ArrayList<ExpericenceDto> (  );
                    response =  GsonImpl.get().toList (str.toString (),ExpericenceDto.class);
                    if(success == 1){
                        //成功
                        sendHandleMessage(GETEXPERIENCE, response);
                    }
                    Log.e("tag", "Post方式请求成功，result--->" + result);
                } else {
                    Log.e("tag", "Post方式请求失败");
                }
                // 关闭连接
                urlConn.disconnect();
            } catch (Exception e) {
                Log.e("tag", e.toString());
            }
        }
    }

    /**
     * 获取师徒顾问关系表数据
     * @param Id
     */
    public void getMasterPupil(int Id) {
        try {
            String baseUrl = url + MethodgetMasterPupil +"?Id="+Id;
            // 新建一个URL对象+"&materi
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
//            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "masterPupil" );
                int success = jsonObject.optInt ( "result" );
                MasterPupilDto response = new MasterPupilDto (  );
                response =  GsonImpl.get().toObject (str.toString (),MasterPupilDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETMASTERPUPIL, response);
                }
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }

    @Override
    protected String getNamespace() {
        return nameSpace;
    }

    @Override
    protected String getServiceUri() {
        return url;
    }
    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("tag", e.toString());
            return null;
        }
    }

    /**
     * 无参数的实例化方法
     *
     * @param context
     */
    public StuAndTecService(Context context) {
        super ( context );
    }
}
