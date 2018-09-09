package com.example.test.myapplicationtest.service;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.test.myapplicationtest.dto.MateriaDto;
import com.example.test.myapplicationtest.dto.ResTeacherDto;
import com.example.test.myapplicationtest.dto.WarningDetailDto;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class ResourceService extends BaseService{
    public static final String url = "http://sundatou.iok.la:18134/train/front/";//http://sundatou.iok.la:18134/train/front/

    public static final String nameSpace = "http://sundatou.iok.la:18134/train/front/";//http://ws.webxml.com.cn/WebServices/WeatherWS.asmx

    //获取师徒教学资料
    public static final String MethodgetMasPupMasterialList = "getMasPupMasterialList";

    //用户下载教学资料记录 有问题
    public static final String MethodsaveDownloadDetail = "saveDownloadDetail";

    //讨论组上传教学资料
    public static final String MethoduploadMaterial = "uploadMaterial";

    //系统教学资料
    public static final String MethodgetMaterialList = "getMaterialList";
    
    public static final int GETMASPUPMASTERIALLIST = 0;
    public static final int GETMATERIALLIST = 1;
    public static final int SAVEDOWNLOADDETAIL =2;
    public static final int UPLOADMATERIAL =3;
    /**
     * 无参数的实例化方法
     *
     * @param context
     */
    public ResourceService(Context context) {
        super ( context );
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
     * 讨论组上传教学资料
     * 和系统教学资料时同一个，参数不同
     file是form表单的name
     * @param memberId
     * @param masterPupilId
     * @param filepath
     */
    public void uploadMaterial(int memberId,int masterPupilId,String filepath ) {
        //使用开源Utils做上传操作
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        try {
            params.put("file", new File(filepath));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace ( );
        }
//url : 请求服务器的url
        asyncHttpClient.post(url + MethoduploadMaterial +"?memberId="+memberId+"&masterPupilId="+masterPupilId, params, new AsyncHttpResponseHandler () {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    sendHandleMessage(UPLOADMATERIAL, "已成功提交");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
            }
        });

//        try {
//            String baseUrl = url + MethoduploadMaterial +"?memberId="+memberId+"&masterPupilId="+masterPupilId+"&file="+file;
//            // 新建一个URL对象
//            URL url = new URL(baseUrl);
//            // 打开一个HttpURLConnection连接
//            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//            // 设置连接超时时间
//            urlConn.setConnectTimeout(15 * 1000);
//            //设置从主机读取数据超时
//            urlConn.setReadTimeout(15 * 1000);
//            // Post请求必须设置允许输出 默认false
//            urlConn.setDoOutput(true);
//            //设置请求允许输入 默认是true
//            urlConn.setDoInput(true);
//            // Post请求不能使用缓存
//            urlConn.setUseCaches(false);
//            // 设置为Post请求
//            urlConn.setRequestMethod("POST");
//            //设置本次连接是否自动处理重定向
//            urlConn.setInstanceFollowRedirects(true);
//            // 配置请求Content-Type
//            urlConn.setRequestProperty("Content-Type", "application/json");
//            // 开始连接
//            urlConn.connect();
//            // 发送请求参数
//            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
////            dos.write(postData);
//            dos.flush();
//            dos.close();
//            // 判断请求是否成功
//            if (urlConn.getResponseCode() == 200) {
//                // 获取返回的数据
//                String result = streamToString(urlConn.getInputStream());
//                JSONObject jsonObject = new JSONObject(result);
////                Object str = jsonObject.opt ( "data" );
//                int success = jsonObject.optInt ( "result" );
////                MateriaDto response = new MateriaDto (  );
////                response =  GsonImpl.get().toObject (str.toString (),MateriaDto.class);
//                if(success == 1){
//                    //成功
//                    sendHandleMessage(UPLOADMATERIAL, "已成功提交");
//                }
//                Log.e("tag", "Post方式请求成功，result--->" + result);
//            } else {
//                Log.e("tag", "Post方式请求失败");
//            }
//            // 关闭连接
//            urlConn.disconnect();
//        } catch (Exception e) {
//            Log.e("tag", e.toString());
//        }
    }

    /**
     * 用户下载教学资料记录
     * 参数说明：如果是系统教学资料，则传参数materialId
     如果是讨论组内教学资料，则传参数masPupMaterialId
     返回说明：result为2的时候，说明两个id都没传
     * @param memberId
     * @param materialId
     * @param masPupMaterialId
     */
    public void saveDownloadDetail(int memberId,int materialId,int masPupMaterialId  ) {
        try {
            String baseUrl = url + MethodsaveDownloadDetail +"?memberId="+memberId+"&materialId="+materialId+"&masPupMaterialId="+masPupMaterialId;
            // 新建一个URL对象
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
                MateriaDto response = new MateriaDto (  );
                response =  GsonImpl.get().toObject (str.toString (),MateriaDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(SAVEDOWNLOADDETAIL, response);
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
     * 系统教学资料
     * @param pageNo
     * @param searchWord
     */
    public void getMaterialList(int pageNo,String searchWord ) {
//        URLEncoder encodeURI = new U
        try {
            String baseUrl = url + MethodgetMaterialList +"?pageNo="+pageNo+"&searchWord="+URLEncoder.encode(URLEncoder.encode(searchWord, "UTF-8"),"UTF-8");
            // 新建一个URL对象
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
                MateriaDto response = new MateriaDto (  );
                response =  GsonImpl.get().toObject (str.toString (),MateriaDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETMATERIALLIST, response);
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
     * 师徒教学资料
     * @param pageNo
     * @param searchWord
     */
    public void getMasPupMasterialList(int pageNo,String searchWord,int masterPupilId) {
        try {
            String baseUrl = url + MethodgetMasPupMasterialList +"?pageNo="+pageNo+"&searchWord="+URLEncoder.encode(URLEncoder.encode(searchWord, "UTF-8"),"UTF-8")+"&masterPupilId="+masterPupilId;
            //合成参数
//            StringBuilder tempParams = new StringBuilder();
//            int pos = 0;
//            for (String key : paramsMap.keySet()) {
//                if (pos > 0) {
//                    tempParams.append("&");
//                }
//                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key).toString (),"utf-8")));
//                pos++;
//            }
//            String params =tempParams.toString();
            // 请求的参数转换为byte数组
//            byte[] postData = params.getBytes();
            // 新建一个URL对象
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
                ResTeacherDto response = new ResTeacherDto (  );
                response =  GsonImpl.get().toObject (str.toString (),ResTeacherDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETMASPUPMASTERIALLIST, response);
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
}
